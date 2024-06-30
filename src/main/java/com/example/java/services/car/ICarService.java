package com.example.java.services.car;

import com.example.java.entities.Car;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    public List<Car> getAllCars();
    public Optional<Car> getCarById(Long id);
    public Car addCar(Car car);
    public Car updateProduct(@NotNull Car car);
    public void deleteCarById(Long id);
}
