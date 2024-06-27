package com.example.java.services.brand;

import com.example.java.repositories.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandService implements IBrandService {
    private final BrandRepo brandRepo;
}
