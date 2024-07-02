package com.example.java.response;

import com.example.java.entities.Rate;
import com.example.java.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Setter
@Getter
@Builder
public class RateResponse {

    private Long id;
    private CarResponse car;
    private UserResponse user;
    private int star;
    private String comment;
    private LocalDate date;

    public static RateResponse fromRate(Rate rate) {
        return RateResponse.builder()
                .id(rate.getId())
                .car(CarResponse.formCar(rate.getCar()))
                .user(UserResponse.fromUser(rate.getUser()))
                .star(rate.getStar())
                .comment(rate.getComment())
                .date(rate.getDate())
                .build();
    }

}