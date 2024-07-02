package com.example.java.controllers;

import com.example.java.entities.Car;
import com.example.java.entities.CarImage;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.response.CarResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.car.ICarService;
import com.example.java.services.car_image.ICarImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final ICarService carService;
    private final ICarImageService carImageService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getCars() {
        List<Car> cars = carService.getCars();

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brands get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(cars.stream().map(CarResponse::formCar).toList())
                        .build()
        );
    }

    @PostMapping(value = "/upload-images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> uploadImages(
            @PathVariable Long id,
            @ModelAttribute("files") List<MultipartFile> files
    ) throws UnsupportedMediaTypeException, PayloadTooLargeException, IOException, IdNotFoundException {
        if(files == null || files.isEmpty()){
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Images file is required.")
                            .build()
            );
        }

        List<CarImage> carImages = carImageService.addImages(id, files);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(OK)
                        .statusCode(OK.value())
                        .message("Images file upload successfully")
                        .data(carImages)
                        .build()
        );
    }

}
