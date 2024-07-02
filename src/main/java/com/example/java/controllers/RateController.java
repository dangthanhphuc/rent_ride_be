package com.example.java.controllers;

import com.example.java.dtos.RateDTO;
import com.example.java.entities.Rate;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.RateResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.rate.IRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rates")
public class RateController {
    private final IRateService rateService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addRate(
            @Valid @RequestBody RateDTO rateDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Rate rate = rateService.addRate(rateDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rate created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RateResponse.fromRate(rate))
                        .build()
        );
    }

    @PutMapping("/update/{rateId}")
    public ResponseEntity<ResponseObject> updateRate(
            @PathVariable Long rateId,
            @Valid @RequestBody RateDTO rateDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Rate rate = rateService
                .updateRate(rateId, rateDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rate updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RateResponse.fromRate(rate))
                        .build()
        );
    }

    @DeleteMapping("/delete/{rateId}")
    public ResponseEntity<ResponseObject> deleteRate(
            @PathVariable Long rateId) throws IdNotFoundException {
        rateService.deleteRate(rateId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rate deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{rateId}")
    public ResponseEntity<ResponseObject> getRate(
            @PathVariable Long rateId
    ) throws IdNotFoundException {
        Rate rate = rateService.getRate(rateId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rate got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RateResponse.fromRate(rate))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getRates() {
        List<Rate> rates = rateService.getRates();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Rate got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(rates.stream().map(RateResponse::fromRate))
                        .build()
        );
    }
}
