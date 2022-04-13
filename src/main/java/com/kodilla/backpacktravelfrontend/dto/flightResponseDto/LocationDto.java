package com.kodilla.backpacktravelfrontend.dto.flightResponseDto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("displayCode")
    private String displayCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("isHighlighted")
    private boolean isHighlighted;
}
