package com.example.java.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    @JsonProperty("max_distance")
    @Min(value = 0, message = "Max distance must be greater than or equal to 0")
    private int maxDistance;

    @JsonProperty("free_distance")
    @Min(value = 0, message = "Free distance must be greater than or equal to 0")
    private int freeDistance;

    @JsonProperty("per_km_fee")
    @Min(value = 0, message = "Per km fee must be greater than or equal to 0")
    private int perKmFee;

}
