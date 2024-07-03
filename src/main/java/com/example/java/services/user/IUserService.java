package com.example.java.services.user;

import com.example.java.dtos.LoginDTO;
import com.example.java.dtos.RegisterDTO;
import com.example.java.dtos.TripDTO;
import com.example.java.dtos.UserDTO;
import com.example.java.entities.Trip;
import com.example.java.entities.User;
import com.example.java.exceptions.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {

    String changeProfileImage(Long id, MultipartFile file) throws IdNotFoundException, UnsupportedMediaTypeException, PayloadTooLargeException, IOException;
    User addUser(UserDTO userDTO);
    User updateUser(Long userId, UserDTO userDTO) throws IdNotFoundException;
    void deleteUser(Long userId) throws IdNotFoundException;
    User getUser(Long userId) throws IdNotFoundException;
    List<User> getUsers();

    User register(RegisterDTO registerDTO) throws IdNotFoundException;
    String login(LoginDTO loginDTO) throws NotMatchException;

    User getUserByToken(String token) throws ExpiredException, DataNotFoundException;


}
