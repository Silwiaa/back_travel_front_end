package com.kodilla.backpacktravelfrontend.domain;

import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LegsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private Boolean enabled;
    private String type;
    private String price;
    private String notFormattedPrice;
    private LocalDateTime arrival;
    private LocalTime duration;
    private int transfers;
    private LocalDateTime comeBack;
    private LocalTime durationComeBack;
    private int transfersComeBack;
    private List<LegsDto> connections;
}
