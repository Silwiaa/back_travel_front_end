package com.kodilla.backpacktravelfrontend.dto.flightResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegsDto {

    @JsonProperty("durationInMinutes")
    private int durationInMinutes;

    @JsonProperty("origin")
    private LocationDto origin;

    @JsonProperty("destination")
    private LocationDto destination;

    @JsonProperty("stopCount")
    private int stopCount;

    @JsonProperty("departure")
    private LocalDateTime departure;

    @JsonProperty("arrival")
    private LocalDateTime arrival;

    @JsonProperty("segments")
    private List<SegmentDto> segmentsDto;
}
