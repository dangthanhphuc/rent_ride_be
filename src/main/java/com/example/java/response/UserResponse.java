package com.example.java.response;

import com.example.java.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class UserResponse {
    private Long id;

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @JsonProperty("img_url")
    private String imgUrl;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    private String username;

    @JsonProperty("role")
    private RoleResponse roleResponse;

    @JsonProperty("license")
    private LicenseResponse licenseResponse;


    public static UserResponse fromUser(User user) {
        UserResponse userResponse =  UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .username(user.getUsername())
                .roleResponse(RoleResponse.formRole(user.getRole()))
                .build();
        if(user.getLicence() != null) {
            userResponse.setLicenseResponse(LicenseResponse.fromLicense(user.getLicence()));
        }
        return userResponse;
    }
}
