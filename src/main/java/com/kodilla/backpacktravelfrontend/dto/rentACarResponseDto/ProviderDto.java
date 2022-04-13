package com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDto {

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("optimised_for_mobile")
    private boolean optimised_for_mobile;

    @JsonProperty("reviews")
    private int reviews;

    @JsonProperty("facilitated_booking_enabled")
    private boolean facilitated_booking_enabled;

    @JsonProperty("provider_name")
    private String provider_name;

    @JsonProperty("errored")
    private boolean errored;

    @JsonProperty("in_progress")
    private boolean in_progress;

    @JsonProperty("logo")
    private String logo;
}
