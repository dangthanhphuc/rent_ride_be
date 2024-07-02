package com.example.java.services.rate;

import com.example.java.entities.Car;
import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CarRepo;
import com.example.java.repositories.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RateService implements IRateService{

    private final RateRepo rateRepo;
    private final CarRepo carRepo;

    @Transactional(rollbackFor = IdNotFoundException.class)
    @Override
    public List<Rate> getRatesByCar(Long carId) throws IdNotFoundException {
        Car existingCar = carRepo.findById(carId)
                .orElseThrow(
                    () -> new IdNotFoundException("Car not found")
                );
        return rateRepo.findRatesByCarId(existingCar.getId());
    }
}
