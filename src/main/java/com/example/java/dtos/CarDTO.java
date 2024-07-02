package com.example.java.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @JsonProperty("license_plate")
    @NotBlank(message = "License Plate cannot be blank")
    private String licensePlate;

    @JsonProperty("address")
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @JsonProperty("mortgage")
    private boolean mortgage;

    @JsonProperty("rent")
    private boolean rent;

    @JsonProperty("price")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private int price;

    @JsonProperty("driver")
    private boolean driver;

    @JsonProperty("max_km")
    @Min(value = 0, message = "Max kilometer must be greater than or equal to 0")
    private int maxKm;

    @JsonProperty("over_fee")
    @Min(value = 0, message = "Over fee must be greater than or equal to 0")
    private int overFee;

    @JsonProperty("model_id")
    @NotNull(message = "Model ID cannot be null")
    @Min(value = 1, message = "Model ID must be greater than 0")
    private Long modelId;

    @JsonProperty("delivery_id")
    private Long deliveryId;

    @JsonProperty("user_id")
    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

}
