package com.example.java.response;

import com.example.java.entities.CarImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
@Builder
public class CarImageResponse {
    private Long id;
    @JsonProperty("car_id")
    private Long carId;
    private String url;


    public static CarImageResponse fromCarImage(CarImage carImage) {
        return CarImageResponse.builder()
                .id(carImage.getId())
                .url("http://localhost:8080/images/car_images/" + carImage.getUrl())
                .carId(carImage.getCar().getId())
                .build();
    }
}
