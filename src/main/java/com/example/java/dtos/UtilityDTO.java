package com.example.java.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilityDTO {

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
