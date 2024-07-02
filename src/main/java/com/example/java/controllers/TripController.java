package com.example.java.controllers;

import com.example.java.dtos.RoleDTO;
import com.example.java.dtos.TripDTO;
import com.example.java.entities.Role;
import com.example.java.entities.Trip;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.ResponseObject;
import com.example.java.response.TripResponse;
import com.example.java.services.role.IRoleService;
import com.example.java.services.trip.ITripService;
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
@RequestMapping("/trips")
public class TripController {
    private final ITripService tripService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addTrip(
            @Valid @RequestBody TripDTO tripDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Trip trip = tripService.addTrip(tripDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Trip created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(TripResponse.formTrip(trip))
                        .build()
        );
    }

    @PutMapping("/update/{tripId}")
    public ResponseEntity<ResponseObject> updateTrip(
            @PathVariable Long tripId,
            @Valid @RequestBody TripDTO tripDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Trip Trip = tripService
                .updateTrip(tripId, tripDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Trip updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(TripResponse.formTrip(Trip))
                        .build()
        );
    }

    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<ResponseObject> deleteTrip(
            @PathVariable Long tripId) throws IdNotFoundException {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Trip deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<ResponseObject> getTrip(
            @PathVariable Long tripId
    ) throws IdNotFoundException {
        Trip Trip = tripService.getTrip(tripId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Trip got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(TripResponse.formTrip(Trip))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getTrips() {
        List<Trip> trips = tripService.getTrips();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Trips got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(trips.stream().map(TripResponse::formTrip))
                        .build()
        );
    }
}
