package com.example.java.services.car;

import com.example.java.entities.Car;
import com.example.java.repositories.CarRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService implements ICarService {
    private final CarRepo carRepo;

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Optional<Car> getCarById(Long id)  {
        return Optional.ofNullable(carRepo.findById(id).orElseThrow(
                () -> new IllegalStateException("Car id : " + id + " is not found")
        ));
    }

    public Car addCar(Car car) {
        return carRepo.save(car);
    }

    public Car updateProduct(@NotNull Car car) {
        Car existingCar = carRepo.findById(car.getId())
                .orElseThrow(() -> new IllegalStateException("Car with ID " +
                        car.getId() + " does not exist."));
        existingCar.setOverFee(car.getOverFee());
        existingCar.setPrice(car.getPrice());
        existingCar.setDelivery(car.getDelivery());
        existingCar.setMaxKm(car.getMaxKm());
        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.setAddress(car.getAddress());
        return carRepo.save(existingCar);
    }
    // Delete a car by its id
    public void deleteCarById(Long id)  {
        if (!carRepo.existsById(id)) {
            throw new IllegalStateException("Car with ID " + id + " does not exist.");
        }
        carRepo.deleteById(id);
    }
}

