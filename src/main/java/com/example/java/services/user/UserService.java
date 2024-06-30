package com.example.java.services.user;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.java.entities.User;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.repositories.UserRepo;
import com.example.java.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepo userRepo;

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
}
