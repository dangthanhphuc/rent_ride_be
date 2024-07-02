package com.example.java.dtos;

import com.example.java.enums.TripStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    @JsonProperty("driver")
    private boolean driver;

    @JsonProperty("start_time")
    @NotNull(message = "Start time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    @NotNull(message = "End time cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @JsonProperty("total_money")
    @NotNull(message = "Total money cannot be null")
    @Min(value = 0, message = "Total money must be greater than or equal to 0")
    private Long totalMoney;

    @JsonProperty("trip_status")
    @NotNull(message = "Trip status cannot be null")
    private TripStatus tripStatus;

    @JsonProperty("car_id")
    @NotNull(message = "Car ID cannot be null")
    @Min(value = 1, message = "Car ID must be greater than 0")
    private Long carId;

    @JsonProperty("user_id")
    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

}