package com.example.java.services.user;

import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {

    String changeProfileImage(Long id, MultipartFile file) throws IdNotFoundException, UnsupportedMediaTypeException, PayloadTooLargeException, IOException;

}
