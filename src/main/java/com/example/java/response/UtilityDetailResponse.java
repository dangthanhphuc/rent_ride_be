package com.example.java.response;

import com.example.java.entities.UtilityDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UtilityDetailResponse {

    private Long id;

    @JsonProperty("utility_id")
    private Long utilityId;

    @JsonProperty("car_id")
    private Long carId;

    public static UtilityDetailResponse fromUtilityDetail(UtilityDetail utilityDetail) {
        return UtilityDetailResponse.builder()
                .id(utilityDetail.getId())
                .carId(utilityDetail.getCar().getId())
                .utilityId(utilityDetail.getUtility().getId())
                .build();
    }
}
