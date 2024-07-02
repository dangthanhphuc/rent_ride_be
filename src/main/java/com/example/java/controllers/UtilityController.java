package com.example.java.controllers;

import com.example.java.entities.Car;
import com.example.java.entities.Utility;
import com.example.java.response.CarResponse;
import com.example.java.response.ResponseObject;
import com.example.java.response.UtilityResponse;
import com.example.java.services.utility.IUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/utilities")
public class UtilityController {

    private final IUtilityService utilityService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getUtilities() {

        List<Utility> utilities = utilityService.getUtilities();

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Utilities get successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(utilities.stream().map(UtilityResponse::getUtility).toList())
                        .build()
        );
    }

}
