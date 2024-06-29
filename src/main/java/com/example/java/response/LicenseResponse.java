package com.example.java.response;

import com.example.java.entities.License;
import com.example.java.enums.LicenseClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class LicenseResponse {
    private Long id;
    private Long no;
    private String name;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("license_class")
    private LicenseClass licenseClass;
    private LocalDate expires;


    public static LicenseResponse fromLicense(License license) {
        return LicenseResponse.builder()
                .id(license.getId())
                .no(license.getNo())
                .name(license.getName())
                .dateOfBirth(license.getDateOfBirth())
                .licenseClass(license.getLicenseClass())
                .expires(license.getExpires())
                .build();
    }
}
