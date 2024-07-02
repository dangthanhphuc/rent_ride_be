package com.example.java.services.car;

import com.example.java.dtos.CarDTO;
import com.example.java.entities.Car;
import com.example.java.entities.Delivery;
import com.example.java.entities.Model;
import com.example.java.entities.User;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CarRepo;
import com.example.java.repositories.DeliveryRepo;
import com.example.java.repositories.ModelRepo;
import com.example.java.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepo carRepo;
    private final ModelRepo modelRepo;
    private final DeliveryRepo deliveryRepo;
    private final UserRepo userRepo;
    @Override
    public List<Car> getCars() {
        return carRepo.findAll();
    }
    @Override
    public Car getCarById(Long id) throws IdNotFoundException {
        return carRepo
                .findById(id) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Car id : " + id +" is not found")
                );
    }
    @Override
    public Car addCar(CarDTO carDTO) {
        // Validate and fetch related entities
        Model model = modelRepo.findById(carDTO.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found with ID: " + carDTO.getModelId()));

        Delivery delivery = null;
        if (carDTO.getDeliveryId() != null) {
            delivery = deliveryRepo.findById(carDTO.getDeliveryId())
                    .orElse(null); // Or throw exception if required
        }

        User user = userRepo.findById(carDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + carDTO.getUserId()));

        // Map CarDTO to Car entity
        Car car = new Car();
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setAddress(carDTO.getAddress());
        car.setMortgage(carDTO.isMortgage());
        car.setRent(carDTO.isRent());
        car.setPrice(carDTO.getPrice());
        car.setDriver(carDTO.isDriver());
        car.setMaxKm(carDTO.getMaxKm());
        car.setOverFee(carDTO.getOverFee());
        car.setModel(model);
        car.setDelivery(delivery);
        car.setUser(user);

        return carRepo.save(car);
    }
    @Override
    public Car updateCar(Long carId, CarDTO carDTO) {
        // Validate and fetch existing Car entity
        Car existingCar = carRepo.findById(carId)
                .orElseThrow(() -> new IllegalStateException("Car with ID " + carId + " does not exist."));

        // Update fields based on CarDTO
        existingCar.setOverFee(carDTO.getOverFee());
        existingCar.setPrice(carDTO.getPrice());
        existingCar.setMaxKm(carDTO.getMaxKm());
        existingCar.setLicensePlate(carDTO.getLicensePlate());
        existingCar.setAddress(carDTO.getAddress());

        if (carDTO.getDeliveryId() != null) {
            Delivery delivery = deliveryRepo.findById(carDTO.getDeliveryId())
                    .orElseThrow(() -> new IllegalArgumentException("Delivery not found with ID: " + carDTO.getDeliveryId()));
            existingCar.setDelivery(delivery);
        } else {
            existingCar.setDelivery(null);
        }

        return carRepo.save(existingCar);
    }

    // Delete a car by its id
    @Override
    public void deleteCar(Long id) throws IdNotFoundException {
        Car existingCar = carRepo
                .findById(id)
                .orElseThrow(
                        () -> new IdNotFoundException("Car id : " + id +" is not found")
                );

        carRepo.delete(existingCar);
    }
}
