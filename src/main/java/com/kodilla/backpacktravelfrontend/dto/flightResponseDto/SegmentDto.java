package com.kodilla.backpacktravelfrontend.dto.flightResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.CarriereDetailsDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LocationDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("origin")
    private LocationDetailsDto originDto;

    @JsonProperty("destination")
    private LocationDetailsDto destinationDto;

    @JsonProperty("departure")
    private LocalDateTime departure;

    @JsonProperty("arrival")
    private LocalDateTime arrival;

    @JsonProperty("durationInMinutes")
    private int durationInMinutes;

    @JsonProperty("flightNumber")
    private int flightNumber;

    @JsonProperty("operatingCarrier")
    private CarriereDetailsDto operatingCarrier;

}
