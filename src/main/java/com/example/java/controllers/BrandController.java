package com.example.java.controllers;

import com.example.java.dtos.BrandDTO;
import com.example.java.dtos.CategoryDTO;
import com.example.java.entities.Brand;
import com.example.java.entities.Category;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.BrandResponse;
import com.example.java.response.CategoryResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.brand.IBrandService;
import com.example.java.services.category.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

    private final IBrandService brandService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addBrand(
            @Valid @RequestBody BrandDTO brandDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Brand brand = brandService.addBrand(brandDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brand created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(BrandResponse.fromBrand(brand))
                        .build()
        );
    }

    @PutMapping("/update/{brandId}")
    public ResponseEntity<ResponseObject> updateBrand(
            @PathVariable Long brandId,
            @Valid @RequestBody BrandDTO brandDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Brand brand = brandService
                .updateBrand(brandId, brandDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brand updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(BrandResponse.fromBrand(brand))
                        .build()
        );
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<ResponseObject> deleteBrand(
            @PathVariable Long brandId) throws IdNotFoundException {
        brandService.deleteBrand(brandId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brand deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<ResponseObject> getBrand(
            @PathVariable Long brandId
    ) throws IdNotFoundException {
        Brand brand = brandService.getBrand(brandId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brand got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(BrandResponse.fromBrand(brand))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getBrands() {
        List<Brand> brands = brandService.getBrands();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Brands got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(brands.stream().map(BrandResponse::fromBrand))
                        .build()
        );
    }

}
