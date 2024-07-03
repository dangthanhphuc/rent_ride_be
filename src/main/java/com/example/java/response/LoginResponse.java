package com.example.java.response;

import com.example.java.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    @JsonProperty()
    private String token;

    @JsonProperty("user_detail")
    private UserResponse userDetail;

//    @JsonProperty("token_type")
//    private String tokenType;

//    @JsonProperty("refresh_token")
//    private String refreshToken;

//    @JsonProperty()
//    private String username;
//
//    @JsonProperty("roles")
//    private List<String> roles;

}
