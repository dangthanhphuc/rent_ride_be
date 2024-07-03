package com.example.java.services.car_image;

import com.example.java.entities.Car;
import com.example.java.entities.CarImage;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.repositories.CarImageRepo;
import com.example.java.repositories.CarRepo;
import com.example.java.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarImageService implements ICarImageService {

    private final CarImageRepo carImageRepo;
    private final CarRepo carRepo;

    @Transactional(rollbackFor = {IdNotFoundException.class, UnsupportedMediaTypeException.class, PayloadTooLargeException.class, IOException.class})
    @Override
    public List<CarImage> addImages(Long id, List<MultipartFile> files) throws IdNotFoundException, UnsupportedMediaTypeException, PayloadTooLargeException, IOException {

        Car existingCar = carRepo.findById(id)
                .orElseThrow(
                        () -> new IdNotFoundException("The id " + id + " does not exist")
                );

        List<CarImage> carImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String imgUrl = FileUtils.storeImage("car_images", file);

            CarImage carImage = new CarImage();
            carImage.setUrl(imgUrl);
            carImage.setCar(existingCar);

            carImages.add(carImage);
        }

        return carImageRepo.saveAll(carImages);
    }

    @Override
    public List<CarImage> getImagesByCar(Long carId) throws IdNotFoundException {
        Car existingCar = carRepo.findById(carId)
                .orElseThrow(
                        () -> new IdNotFoundException("Car not found")
                );

        return carImageRepo.findCarImagesByCarId(carId);
    }
}
