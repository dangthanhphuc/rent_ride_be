package com.example.java.controllers;


import com.example.java.dtos.UtilityDTO;
import com.example.java.entities.Utility;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.ResponseObject;
import com.example.java.response.UtilityResponse;
import com.example.java.services.utility.IUtilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
                        .data(utilities.stream().map(UtilityResponse::formUtility).toList())

                        .build()
        );
    }


    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addUtility(
            @Valid @RequestBody UtilityDTO utilityDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Utility utility = utilityService.addUtility(utilityDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Utility created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(UtilityResponse.formUtility(utility))
                        .build()
        );
    }

    @PutMapping("/update/{utilityId}")
    public ResponseEntity<ResponseObject> updateUtility(
            @PathVariable Long utilityId,
            @Valid @RequestBody UtilityDTO utilityDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Utility utility = utilityService
                .updateUtility(utilityId, utilityDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Utility updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(UtilityResponse.formUtility(utility))
                        .build()
        );
    }

    @DeleteMapping("/delete/{utilityId}")
    public ResponseEntity<ResponseObject> deleteUtility(
            @PathVariable Long utilityId) throws IdNotFoundException {
        utilityService.deleteUtility(utilityId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Utility deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{utilityId}")
    public ResponseEntity<ResponseObject> getUtility(
            @PathVariable Long utilityId
    ) throws IdNotFoundException {
        Utility utility = utilityService.getUtility(utilityId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Utility got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(UtilityResponse.formUtility(utility))
                        .build()
        );
    }

}
