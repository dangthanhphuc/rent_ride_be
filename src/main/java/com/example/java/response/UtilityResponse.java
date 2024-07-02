package com.example.java.response;

import com.example.java.entities.Utility;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UtilityResponse {
    private Long id;
    private String name;

    public static UtilityResponse getUtility(Utility utility) {
        return UtilityResponse.builder()
                .id(utility.getId())
                .name(utility.getName())
                .build();
    }
}
