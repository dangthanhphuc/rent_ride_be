package com.example.java.services.brand;


import com.example.java.dtos.BrandDTO;
import com.example.java.entities.Brand;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IBrandService {

    Brand addBrand(BrandDTO brandDTO);
    Brand updateBrand(Long brandId, BrandDTO brandDTO) throws IdNotFoundException;
    void deleteBrand(Long brandId) throws IdNotFoundException;
    Brand getBrand(Long brandId) throws IdNotFoundException;

    List<Brand> getBrands();
}
