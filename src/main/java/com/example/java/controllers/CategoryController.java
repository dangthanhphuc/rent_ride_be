package com.example.java.controllers;

import com.example.java.dtos.CategoryDTO;
import com.example.java.entities.Category;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.CategoryResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Category Category = categoryService.addCategory(categoryDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Category created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CategoryResponse.fromCategory(Category))
                        .build()
        );
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ResponseObject> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Category Category = categoryService
                .updateCategory(categoryId, categoryDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Category updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CategoryResponse.fromCategory(Category))
                        .build()
        );
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ResponseObject> deleteCategory(
            @PathVariable Long categoryId) throws IdNotFoundException {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Category deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseObject> getCategory(
            @PathVariable Long categoryId
    ) throws IdNotFoundException {
        Category category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Category got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(CategoryResponse.fromCategory(category))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Categories got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(categories.stream().map(CategoryResponse::fromCategory))
                        .build()
        );
    }
}