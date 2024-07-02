package com.example.java.services.brand;

import com.example.java.dtos.BrandDTO;
import com.example.java.entities.Brand;
import com.example.java.entities.Category;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {
    private final BrandRepo brandRepo;
    @Override
    public Brand addBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();

        brand.setName(brandDTO.getName());
        return brandRepo.save(brand);
    }

    @Override
    public Brand updateBrand(Long brandId, BrandDTO brandDTO) throws IdNotFoundException {
        Brand existingBrand = brandRepo
                .findById(brandId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Brand id : " + brandId +" is not found")
                );

        existingBrand.setName(brandDTO.getName());
        return brandRepo.save(existingBrand);
    }


    @Override
    public void deleteBrand(Long brandId) throws IdNotFoundException {
        Brand existingBrand = brandRepo
                .findById(brandId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Brand id : " + brandId +" is not found")
                );
        brandRepo.delete(existingBrand);
    }

    @Override
    public Brand getBrand(Long brandId) throws IdNotFoundException {
        return brandRepo
                .findById(brandId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Brand id : " + brandId +" is not found")
                );
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepo.findAll();
    }
}
