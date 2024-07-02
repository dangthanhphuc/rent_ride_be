package com.example.java.dtos;

import com.example.java.enums.Fuel;
import com.example.java.enums.Gearbox;
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
public class ModelDTO {

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @JsonProperty("seat")
    @Min(value = 1, message = "Seat must be greater than 0")
    private int seat;

    @JsonProperty("production_year")
    @Min(value = 2005, message = "Production year must be a valid year")
    private int productionYear;

    @JsonProperty("brand_id")
    @NotNull(message = "Brand ID cannot be null")
    @Min(value = 1, message = "Brand ID must be greater than 0")
    private Long brandId;

    @JsonProperty("category_id")
    @NotNull(message = "Category ID cannot be null")
    @Min(value = 1, message = "Category ID must be greater than 0")
    private Long categoryId;

    @JsonProperty("fuel")
    @NotNull(message = "Fuel cannot be null")
    private Fuel fuel;

    @JsonProperty("gearbox")
    @NotNull(message = "Gearbox cannot be null")
    private Gearbox gearbox;

}