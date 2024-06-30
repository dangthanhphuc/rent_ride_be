package com.example.java.services.car;

import com.example.java.entities.Car;
import com.example.java.repositories.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepo carRepo;
    @Override
    public List<Car> getCars() {
        return carRepo.findAll();
    }
}
