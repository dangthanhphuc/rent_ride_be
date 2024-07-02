package com.example.java.response;

import com.example.java.entities.Trip;
import com.example.java.enums.TripStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TripResponse {

    private Long id;

    @JsonProperty("driver")
    private boolean driver;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("total_money")
    private Long totalMoney;

    @JsonProperty("trip_status")
    private TripStatus tripStatus;

    @JsonProperty("car")
    private CarResponse carResponse;

    @JsonProperty("user")
    private UserResponse userResponse;

    public static TripResponse formTrip(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .driver(trip.isDriver())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .totalMoney(trip.getTotalMoney())
                .tripStatus(trip.getTripStatus())
                .carResponse(CarResponse.formCar(trip.getCar()))
                .userResponse(UserResponse.fromUser(trip.getUser()))
                .build();
    }
}