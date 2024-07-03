package com.example.java.controllers;

import com.example.java.entities.CarImage;
import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.response.CarImageResponse;
import com.example.java.response.RateResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.car_image.ICarImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/car_images")
public class CarImageController {

    private final ICarImageService carImageService;

    @GetMapping("/car_id/{id}")
    public ResponseEntity<ResponseObject> getImageFromCar(
            @PathVariable("id") Long carId
    ) throws IdNotFoundException {
        List<CarImage> carImages = carImageService.getImagesByCar(carId);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car images get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(carImages.stream().map(CarImageResponse::fromCarImage).toList())
                        .build()
        );
    }

}
