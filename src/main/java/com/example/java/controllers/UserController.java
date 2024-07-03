package com.example.java.controllers;

import com.example.java.dtos.LoginDTO;
import com.example.java.dtos.RegisterDTO;
import com.example.java.entities.User;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.LoginResponse;
import com.example.java.response.ResponseObject;
import com.example.java.response.UserResponse;
import com.example.java.services.user.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @PostMapping(value = "/upload-profile-image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> uploadProfileImage(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) throws UnsupportedMediaTypeException, PayloadTooLargeException, IOException, IdNotFoundException {

        if(file ==null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Image file is required.")
                        .build()
            );
        }

        String imgUrl = userService.changeProfileImage(id, file);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Successfully update profile images!")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data("http://localhost:8080/images/profile_images/" + imgUrl)
                        .build()
        );

    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterDTO registerDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if(isInvalid != null) {
            return isInvalid;
        }

//        if(!registerDTO.getPassword().equals(registerPatientDTO.getRetypePassword())){
//            return ResponseEntity.badRequest().body(
//                    ResponseObject.builder()
//                            .timeStamp(LocalDateTime.now())
//                            .message("Password not match with retype password !")
//                            .status(BAD_REQUEST)
//                            .statusCode(BAD_REQUEST.value())
//                            .data(null)
//                            .build()
//            );

        User user = userService.register(registerDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Patient created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(UserResponse.fromUser(user))
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(
            @RequestBody LoginDTO loginDTO,
            HttpServletRequest request
    ) throws Exception {
        String token = userService.login(loginDTO);
        User userDetail = userService.getUserByToken(token);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(token)
                .userDetail(UserResponse.fromUser(userDetail))
                .build();

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("User login successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(loginResponse)
                        .build()
        );
    }

    // reset password

}
