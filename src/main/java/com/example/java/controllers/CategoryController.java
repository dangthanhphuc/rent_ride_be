package com.example.java.controllers;

import com.example.java.entities.Brand;
import com.example.java.entities.Category;
import com.example.java.response.BrandResponse;
import com.example.java.response.CategoryResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.category.ICategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getCategories() {
        List<Category> categories = categoryService.getCategories();

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Categories get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(categories.stream().map(CategoryResponse::fromCategory).toList())
                        .build()
        );
    }

}
