package com.example.java.dtos;

import com.example.java.enums.LicenseClass;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDTO {

    @JsonProperty("no")
    private Long no;

    @JsonProperty("license_class")
    @NotNull(message = "License class cannot be null")
    private LicenseClass licenseClass;

    @JsonProperty("expires")
    @NotNull(message = "Expiration date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expires;

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @JsonProperty("date_of_birth")
    @NotNull(message = "Date of birth cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}