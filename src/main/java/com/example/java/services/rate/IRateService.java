package com.example.java.services.rate;

import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IRateService {
    List<Rate> getRatesByCar(Long carId) throws IdNotFoundException;
}
