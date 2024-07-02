package com.example.java.services.user;

import com.example.java.dtos.TripDTO;
import com.example.java.dtos.UserDTO;
import com.example.java.entities.Trip;
import com.example.java.entities.User;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
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
}
