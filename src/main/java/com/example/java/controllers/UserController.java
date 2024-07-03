package com.example.java.controllers;

import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.response.ResponseObject;
import com.example.java.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

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

    // login
    // reset password
}
