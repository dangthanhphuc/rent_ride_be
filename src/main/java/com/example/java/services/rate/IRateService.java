package com.example.java.services.rate;


import com.example.java.dtos.CategoryDTO;
import com.example.java.dtos.RateDTO;
import com.example.java.entities.Category;

import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IRateService {

    List<Rate> getRatesByCar(Long carId) throws IdNotFoundException;

    Rate addRate(RateDTO rateDTO);
    Rate updateRate(Long rateId, RateDTO rateDTO) throws IdNotFoundException;
    void deleteRate(Long rateId) throws IdNotFoundException;
    Rate getRate(Long rateId) throws IdNotFoundException;
    List<Rate> getRates();


}
