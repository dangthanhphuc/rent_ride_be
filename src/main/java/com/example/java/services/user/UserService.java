package com.example.java.services.user;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.java.dtos.LoginDTO;
import com.example.java.dtos.RegisterDTO;
import com.example.java.dtos.UpdatePassDTO;
import com.example.java.dtos.UserDTO;
import com.example.java.entities.License;
import com.example.java.entities.Role;
import com.example.java.entities.Trip;
import com.example.java.entities.User;
import com.example.java.exceptions.*;
import com.example.java.repositories.LicenseRepo;
import com.example.java.repositories.RoleRepo;
import com.example.java.repositories.UserRepo;
import com.example.java.utils.FileUtils;
import com.example.java.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final LicenseRepo licenseRepo;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    @Transactional(rollbackFor = {IdNotFoundException.class, UnsupportedMediaTypeException.class, PayloadTooLargeException.class, IOException.class})
    @Override
    public String changeProfileImage(Long id, MultipartFile file) throws IdNotFoundException, UnsupportedMediaTypeException, PayloadTooLargeException, IOException {

        User existingUser = userRepo.findById(id)
                .orElseThrow(
                    () -> new IdNotFoundException("User " + id + " not found !")
                );

        String imgUrl = FileUtils.storeImage("profile_images", file);
        FileUtils.deleteFile("images/profile_images", existingUser.getImgUrl());

        existingUser.setImgUrl(imgUrl);
        userRepo.save(existingUser);

        return imgUrl;
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setImgUrl(userDTO.getImgUrl());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Hash the password in a real application

        License license = licenseRepo.findById(userDTO.getLicenseId())
                .orElseThrow(() -> new IllegalArgumentException("License not found with ID: " + userDTO.getLicenseId()));
        user.setLicence(license);

        Role role = roleRepo.findById(userDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + userDTO.getRoleId()));
        user.setRole(role);

        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long userId, UserDTO userDTO) throws IdNotFoundException {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " does not exist."));

        existingUser.setName(userDTO.getName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        existingUser.setImgUrl(userDTO.getImgUrl());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword()); // Hash the password in a real application

        if (userDTO.getLicenseId() != null) {
            License license = licenseRepo.findById(userDTO.getLicenseId())
                    .orElseThrow(() -> new IllegalArgumentException("License not found with ID: " + userDTO.getLicenseId()));
            existingUser.setLicence(license);
        }

        if (userDTO.getRoleId() != null) {
            Role role = roleRepo.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + userDTO.getRoleId()));
            existingUser.setRole(role);
        }

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) throws IdNotFoundException {
        User existingUser = userRepo
                .findById(userId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("User id : " + userId +" is not found")
                );
        userRepo.delete(existingUser);
    }

    @Override
    public User getUser(Long userId) throws IdNotFoundException {
        return userRepo
                .findById(userId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("User id : " + userId +" is not found")
                );
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void resetPassword(Long userId , UpdatePassDTO updatePassDTO) throws Exception {

        User existingUser = userRepo.findById(userId)
                .orElseThrow(
                        () -> new IdNotFoundException("User id not found")
                );

        boolean isMatchesPass = passwordEncoder.matches(updatePassDTO.getOldPass(), existingUser.getPassword());
        if( !isMatchesPass) {
            throw new Exception("Old password does not match !");
        }

        String passwordEncode = passwordEncoder.encode(updatePassDTO.getNewPass());

        existingUser.setPassword(passwordEncode);

        userRepo.save(existingUser);

        // Chưa clear token

    }

    @Override
    public User register(RegisterDTO registerDTO) throws IdNotFoundException {
        Role role = roleRepo.findById(1L)
                .orElseThrow(
                        () -> new IdNotFoundException("Role id USER is not found")
                );

        // Encode Password
        String passwordEncoded = passwordEncoder.encode(registerDTO.getPassword());

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoded)
                .email(registerDTO.getEmail())
                .name(registerDTO.getName())
                .phoneNumber(registerDTO.getPhoneNumber())
                .dateOfBirth(registerDTO.getDateOfBirth())
                .role(role)
                .build();

        return userRepo.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) throws NotMatchException {
        User existingUser = userRepo.findByUsername(loginDTO.getUsername())
                .orElseThrow(
                        () -> new NotMatchException("Username not found !")
                );

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword(),
                existingUser.getAuthorities()
        ); // Tạo authenticationToken đáng tin cậy

        authenticationManager.authenticate(authenticationToken); // Xác thực thông tin của authenticationToken

        return jwtTokenUtils.generateToken(existingUser);
    }

    @Override
    public User getUserByToken(String token) throws ExpiredException, DataNotFoundException {
        if(jwtTokenUtils.isTokenExpired(token)){
            throw new ExpiredException("Token is expired");
        }
        String username = jwtTokenUtils.getUsernameFromToken(token);
        User user = userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new DataNotFoundException("Username not found !")
                );

        return user;
    }
}
