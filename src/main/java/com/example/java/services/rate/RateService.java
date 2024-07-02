package com.example.java.services.rate;

import com.example.java.dtos.RateDTO;
import com.example.java.entities.*;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CarRepo;
import com.example.java.repositories.RateRepo;
import com.example.java.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RateService implements IRateService{
    private final RateRepo rateRepo;
    private final CarRepo carRepo;
    private final UserRepo userRepo;
    @Override
    public Rate addRate(RateDTO rateDTO) {
        Rate rate = new Rate();

        rate.setStar(rateDTO.getStar());
        rate.setDate(rateDTO.getDate());
        rate.setComment(rateDTO.getComment());
        Car car = carRepo.findById(rateDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        User user = userRepo.findById(rateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        rate.setCar(car);
        rate.setUser(user);
        return rateRepo.save(rate);
    }

    @Override
    public Rate updateRate(Long rateId, RateDTO rateDTO) throws IdNotFoundException {
        Rate existingRate = rateRepo.findById(rateId)
                .orElseThrow(() -> new IdNotFoundException("Rate id: " + rateDTO + " is not found"));
        existingRate.setStar(rateDTO.getStar());
        existingRate.setDate(rateDTO.getDate());
        existingRate.setComment(rateDTO.getComment());
        Car car = carRepo.findById(rateDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        User user = userRepo.findById(rateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingRate.setCar(car);
        existingRate.setUser(user);
        return rateRepo.save(existingRate);
    }

    @Override
    public void deleteRate(Long rateId) throws IdNotFoundException {
        Rate existingRate = rateRepo
                .findById(rateId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Rate id : " + rateId +" is not found")
                );
        rateRepo.delete(existingRate);
    }

    @Override
    public Rate getRate(Long rateId) throws IdNotFoundException {
        return rateRepo
                .findById(rateId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Rate id : " + rateId +" is not found")
                );
    }

    @Override
    public List<Rate> getRates() {
        return rateRepo.findAll();
    }
}
