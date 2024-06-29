package com.example.java.response;

import com.example.java.entities.Brand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BrandResponse {

    private Long id;

    private String name;

    public static BrandResponse fromBrand(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
