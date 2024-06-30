package com.example.java.services.car_image;

import com.example.java.entities.CarImage;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICarImageService {

    List<CarImage> addImages(Long id, List<MultipartFile> files) throws IdNotFoundException, UnsupportedMediaTypeException, PayloadTooLargeException, IOException;

}
