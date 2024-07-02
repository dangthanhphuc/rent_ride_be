package com.example.java.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
