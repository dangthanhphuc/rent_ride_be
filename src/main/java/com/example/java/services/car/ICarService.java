package com.example.java.services.car;

import com.example.java.dtos.CarDTO;
import com.example.java.entities.Car;
import com.example.java.exceptions.IdNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    List<Car> getCars();
    public Car getCarById(Long id) throws IdNotFoundException;
    public Car addCar(CarDTO carDTO) throws IdNotFoundException;;
    public Car updateCar(Long carId, CarDTO carDTO) throws IdNotFoundException;;
    public void deleteCar(Long id) throws IdNotFoundException;
}
