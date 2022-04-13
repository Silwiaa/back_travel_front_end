package com.kodilla.backpacktravelfrontend.dto;

import com.kodilla.backpacktravelfrontend.dto.airportDto.AirportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaDto {
    private int passengers;
    private String originCountry;
    private AirportDto originAirport;
    private String destinationCountry;
    private AirportDto destinationAirport;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private String currency;
    private String way;
}
