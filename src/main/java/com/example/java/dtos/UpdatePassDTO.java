package com.example.java.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassDTO {

    @JsonProperty("old_pass")
    @NotBlank(message = "The old message cannot be blank")
    private String oldPass;

    @JsonProperty("new_pass")
    @NotBlank(message = "The new message cannot be blank")
    private String newPass;

    @JsonProperty("confirm_pass")
    @NotBlank(message = "The confirm message cannot be blank")
    private String confirmPass;
}
