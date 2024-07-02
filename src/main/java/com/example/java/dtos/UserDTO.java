package com.example.java.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("img_url")
    private String imgUrl;

    @JsonProperty("username")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @JsonProperty("license_id")
    private Long licenseId;

    @JsonProperty("role_id")
    @NotNull(message = "Role ID cannot be null")
    @Min(value = 1, message = "Role ID must be greater than 0")
    private Long roleId;
}