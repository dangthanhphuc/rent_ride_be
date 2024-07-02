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

    @JsonProperty("star")
    private int star;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("car")
    private CarResponse carResponse;

    @JsonProperty("user")
    private UserResponse userResponse;

    public static RateResponse fromRate(Rate rate) {
        RateResponse rateResponse = RateResponse.builder()
                .id(rate.getId())
                .star(rate.getStar())
                .comment(rate.getComment())
                .date(rate.getDate())
                .carResponse(CarResponse.formCar(rate.getCar()))
                .userResponse(UserResponse.fromUser(rate.getUser()))
                .build();

        return rateResponse;
    }
}
