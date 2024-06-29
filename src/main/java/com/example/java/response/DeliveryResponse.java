package com.example.java.response;

import com.example.java.entities.Delivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeliveryResponse {

    private Long id;

    // Khoảng cách giao tối đa
    @JsonProperty("max_distance")
    private int maxDistance;

    // Khoảng cách giao miễn phí
    @JsonProperty("free_distance")
    private int freeDistance;

    // Phí giao nhận xe mỗi km
    @JsonProperty("per_km_fee")
    private int perKmFee;

    public static DeliveryResponse fromDelivery(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .maxDistance(delivery.getMaxDistance())
                .freeDistance(delivery.getFreeDistance())
                .perKmFee(delivery.getPerKmFee())
                .build();
    }

}
