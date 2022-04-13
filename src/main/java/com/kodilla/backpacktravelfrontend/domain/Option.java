package com.kodilla.backpacktravelfrontend.domain;

import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LegsDto;
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
public class Option {
    private Boolean enabled;
    private String type;
    private String notFormattedPrice;
    private LocalDateTime arrivalAtDestination;
    private int transfersDuringDestinationFlight;
    private LocalDateTime comeBackArrival;
    private int transfersDuringComeBackFlight;
    private String price;
    private List<LegsDto> connections;
}
