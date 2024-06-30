package com.example.java.response;

import com.example.java.entities.Category;
import com.example.java.entities.Model;
import com.example.java.enums.Fuel;
import com.example.java.enums.Gearbox;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class ModelResponse {

    private Long id;

    private String name;

    @JsonProperty("production_year")
    private int productionYear;

    private int seats;

    private Fuel fuel;

    private Gearbox gearbox;

    @JsonProperty("brand")
    private BrandResponse brandResponse;

    @JsonProperty("category")
    private CategoryResponse categoryResponse;

    public static ModelResponse fromModel(Model model) {
        return ModelResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .productionYear(model.getProductionYear())
                .seats(model.getSeat())
                .fuel(model.getFuel())
                .gearbox(model.getGearbox())
                .brandResponse(BrandResponse.fromBrand(model.getBrand()))
                .categoryResponse(CategoryResponse.fromCategory(model.getCategory()))
                .build();
    }
}
