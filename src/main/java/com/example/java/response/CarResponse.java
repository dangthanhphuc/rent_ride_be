package com.example.java.response;

import com.example.java.entities.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Setter
@Getter
@Builder
public class CarResponse {

    private Long id;

    @JsonProperty("license_plate")
    private String licensePlate;

    private String address;

    private boolean mortgage;

    private boolean rent;

    private boolean driver;

    @JsonProperty("max_km")
    private int maxKm;

    @JsonProperty("over_fee")
    private int overFee;

    @JsonProperty("model")
    private ModelResponse modelResponse;

    @JsonProperty("delivery")
    private DeliveryResponse deliveryResponse;

    @JsonProperty("user")
    private UserResponse userResponse;

    public static CarResponse formCar(Car car) {
        CarResponse carResponse = CarResponse.builder()
                .id(car.getId())
                .licensePlate(car.getLicensePlate())
                .address(car.getAddress())
                .mortgage(car.isMortgage())
                .rent(car.isRent())
                .driver(car.isDriver())
                .maxKm(car.getMaxKm())
                .overFee(car.getOverFee())
                .modelResponse(ModelResponse.fromModel(car.getModel()))
                .userResponse(UserResponse.fromUser(car.getUser()))
                .build();

        if(car.getDelivery() != null) {
            carResponse.setDeliveryResponse(DeliveryResponse.fromDelivery(car.getDelivery()));
        }

        return carResponse;
    }
}
