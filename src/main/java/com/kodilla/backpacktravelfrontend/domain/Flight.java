package com.kodilla.backpacktravelfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String type;
    private String origin;
    private String arrival;
    private BigDecimal price;
    private LocalTime duration;
    private Integer stops;
    private List<Transfer> transfer;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String company;
}
