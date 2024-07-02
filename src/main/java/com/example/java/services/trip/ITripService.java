package com.example.java.services.trip;

import com.example.java.dtos.RateDTO;
import com.example.java.dtos.TripDTO;
import com.example.java.entities.Rate;
import com.example.java.entities.Trip;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface ITripService {
    Trip addTrip(TripDTO tripDTO);
    Trip updateTrip(Long tripId, TripDTO tripDTO) throws IdNotFoundException;
    void deleteTrip(Long tripId) throws IdNotFoundException;
    Trip getTrip(Long tripId) throws IdNotFoundException;
    List<Trip> getTrips();
}
