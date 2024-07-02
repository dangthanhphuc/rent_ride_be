package com.example.java.response;

import com.example.java.entities.Utility;

public class UtilityResponse {
    private Long id;

    private String name;

    public static RoleResponse formUtility(Utility utility) {
        return RoleResponse.builder()
                .id(utility.getId())
                .name(utility.getName())
                .build();
    }
}
