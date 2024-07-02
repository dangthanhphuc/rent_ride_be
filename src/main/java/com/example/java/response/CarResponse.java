package com.example.java.response;

import com.example.java.entities.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.util.List;

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

    private int price;

    @JsonProperty("max_km")
    private int maxKm;

    @JsonProperty("over_fee")
    private int overFee;

    private boolean instant;

    @JsonProperty("model")
    private ModelResponse modelResponse;

    @JsonProperty("delivery")
    private DeliveryResponse deliveryResponse;

    @JsonProperty("user")
    private UserResponse userResponse;

    @JsonProperty("utility_details")
    private List<UtilityDetailResponse> utilityDetails;

    public static CarResponse formCar(Car car) {
        CarResponse carResponse = CarResponse.builder()
                .id(car.getId())
                .licensePlate(car.getLicensePlate())
                .address(car.getAddress())
                .price(car.getPrice())
                .instant(car.isInstant())
                .mortgage(car.isMortgage())
                .rent(car.isRent())
                .driver(car.isDriver())
                .maxKm(car.getMaxKm())
                .overFee(car.getOverFee())
                .modelResponse(ModelResponse.fromModel(car.getModel()))
                .userResponse(UserResponse.fromUser(car.getUser()))
                .utilityDetails(car.getUtilityDetail().stream().map(UtilityDetailResponse::fromUtilityDetail).toList())
                .build();

        if(car.getDelivery() != null) {
            carResponse.setDeliveryResponse(DeliveryResponse.fromDelivery(car.getDelivery()));
        }

        return carResponse;
    }
}
