package com.example.java.controllers;

import com.example.java.dtos.CarDTO;
import com.example.java.entities.Car;
import com.example.java.entities.CarImage;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.exceptions.PayloadTooLargeException;
import com.example.java.exceptions.UnsupportedMediaTypeException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.CarResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.car.ICarService;
import com.example.java.services.car_image.ICarImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addCar(
           @RequestBody CarDTO carDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if(isInvalid != null) {
            return isInvalid;
        }
        Car car =carService.addCar(carDTO);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CarResponse.formCar(car))
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCar (
            @PathVariable Long id
    ) throws IdNotFoundException {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CarResponse.formCar(car))
                        .build()
        );
    }
    //    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{carId}")
    public ResponseEntity<ResponseObject> updateCar(
            @PathVariable Long id,
            @Valid @RequestBody CarDTO carDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if(isInvalid != null) {
            return isInvalid;
        }
        Car car = carService.updateCar(id, carDTO);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CarResponse.formCar(car))
                        .build()
        );
    }
    //    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<ResponseObject> deleteCar(
            @PathVariable Long id) throws IdNotFoundException {
        carService.deleteCar(id);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }
}
