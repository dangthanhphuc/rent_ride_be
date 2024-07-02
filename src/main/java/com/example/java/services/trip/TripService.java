package com.example.java.services.trip;

import com.example.java.dtos.TripDTO;
import com.example.java.entities.Car;
import com.example.java.entities.Rate;
import com.example.java.entities.Trip;
import com.example.java.entities.User;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CarRepo;
import com.example.java.repositories.TripRepo;
import com.example.java.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripService implements ITripService{
    private final TripRepo tripRepo;
    private final CarRepo carRepo;
    private final UserRepo userRepo;
    @Override
    public Trip addTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setDriver(tripDTO.isDriver());
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        trip.setTotalMoney(tripDTO.getTotalMoney());
        trip.setTripStatus(tripDTO.getTripStatus());

        Car car = carRepo.findById(tripDTO.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + tripDTO.getCarId()));
        trip.setCar(car);

        User user = userRepo.findById(tripDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + tripDTO.getUserId()));
        trip.setUser(user);

        return tripRepo.save(trip);
    }

    @Override
    public Trip updateTrip(Long tripId, TripDTO tripDTO) throws IdNotFoundException {
        Trip existingTrip = tripRepo.findById(tripId)
                .orElseThrow(() -> new IdNotFoundException("Trip with ID " + tripId + " is not found"));

        existingTrip.setDriver(tripDTO.isDriver());
        existingTrip.setStartTime(tripDTO.getStartTime());
        existingTrip.setEndTime(tripDTO.getEndTime());
        existingTrip.setTotalMoney(tripDTO.getTotalMoney());
        existingTrip.setTripStatus(tripDTO.getTripStatus());

        if (tripDTO.getCarId() != null) {
            Car car = carRepo.findById(tripDTO.getCarId())
                    .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + tripDTO.getCarId()));
            existingTrip.setCar(car);
        }

        if (tripDTO.getUserId() != null) {
            User user = userRepo.findById(tripDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + tripDTO.getUserId()));
            existingTrip.setUser(user);
        }

        return tripRepo.save(existingTrip);
    }

    @Override
    public void deleteTrip(Long tripId) throws IdNotFoundException {
        Trip existingTrip = tripRepo
                .findById(tripId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Trip id : " + tripId +" is not found")
                );
        tripRepo.delete(existingTrip);
    }

    @Override
    public Trip getTrip(Long tripId) throws IdNotFoundException {
        return tripRepo
                .findById(tripId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Trip id : " + tripId +" is not found")
                );
    }

    @Override
    public List<Trip> getTrips() {
        return tripRepo.findAll();
    }
}
