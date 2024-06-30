package com.example.java.controllers;

import com.example.java.entities.Car;
import com.example.java.response.CarResponse;
import com.example.java.services.car.ICarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final ICarService carService;

    @GetMapping
    public List<CarResponse> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return cars.stream()
                .map(CarResponse::formCar)
                .collect(Collectors.toList());
    }
    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id)
                .orElseThrow(() -> new RuntimeException("Car not found on :: "
                        + id));
        return ResponseEntity.ok().body(car);
    }
    //    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                                 @RequestBody Car cars) {
        Car car = carService.getCarById(id)
                .orElseThrow(() -> new RuntimeException("Car not found on :: "
                        + id));
        car.setOverFee(cars.getOverFee());
        car.setPrice(cars.getPrice());
        car.setDelivery(cars.getDelivery());
        car.setMaxKm(cars.getMaxKm());
        car.setLicensePlate(cars.getLicensePlate());
        car.setAddress(cars.getAddress());
        final Car updatedCar =carService.addCar(car);
        return ResponseEntity.ok(updatedCar);
    }
    //    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Car car = carService.getCarById(id)
                .orElseThrow(() -> new RuntimeException("Car not found on :: "+ id));
        carService.deleteCarById(id);
        return ResponseEntity.ok().build();
    }
}
