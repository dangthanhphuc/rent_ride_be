package com.example.java.controllers;

import com.example.java.services.brand.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class BrandController {

    private final IBrandService brandService;

    // Get all
    // get by id
    // add
    // update
    // delete

}
