package com.example.java.services.brand;

import com.example.java.entities.Brand;
import com.example.java.repositories.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {
    private final BrandRepo brandRepo;

    @Override
    public List<Brand> getBrands() {
        return brandRepo.findAll();
    }
}
