package com.example.java.controllers;

import com.example.java.entities.Brand;
import com.example.java.entities.Car;
import com.example.java.response.BrandResponse;
import com.example.java.response.CarResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.brand.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

    private final IBrandService brandService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getBrands() {
        List<Brand> brands = brandService.getBrands();

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brands get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(brands.stream().map(BrandResponse::fromBrand).toList())
                        .build()
        );
    }

    // get by id
    // add
    // update
    // delete

}
