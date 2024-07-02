package com.example.java.controllers;

import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.response.CarResponse;
import com.example.java.response.RateResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.rate.IRateService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rates")
public class RateController {

    private final IRateService rateService;

    @GetMapping("/car_id/{id}")
    public ResponseEntity<ResponseObject> getRatesFromCar(
            @PathVariable("id") Long carId
    ) throws IdNotFoundException {
        List<Rate> rates = rateService.getRatesByCar(carId);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rates get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(rates.stream().map(RateResponse::fromRate).toList())
                        .build()
        );
    }

}
