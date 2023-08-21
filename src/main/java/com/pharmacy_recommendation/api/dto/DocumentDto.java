package com.pharmacy_recommendation.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("x")
    private String longitude;
    @JsonProperty("y")
    private String latitude;
}
