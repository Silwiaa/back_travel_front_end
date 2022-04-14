package com.kodilla.backpacktravelfrontend.service;

import com.kodilla.backpacktravelfrontend.client.SkyscannerClient;
import com.kodilla.backpacktravelfrontend.dto.*;
import com.kodilla.backpacktravelfrontend.dto.airportDto.AirportDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.BestFlightDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.ItineraryDto;
import com.kodilla.backpacktravelfrontend.dto.locationRentACarDto.LocationRentACarDto;
import com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto.RentACarResponseDto;
import com.vaadin.flow.component.notification.Notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SkyscannerService {
    private List<AirportDto> airportDtoList;
    private BestFlightDto bestFlightDto;
    private List<LocationRentACarDto> locationRentACarDtoList;
    private RentACarResponseDto rentACarResponseDto;
    private static SkyscannerService skyscannerService;

    private SkyscannerClient skyscannerClient = new SkyscannerClient();

    public static SkyscannerService getInstance() {
        if (skyscannerService == null) {
            skyscannerService = new SkyscannerService();
        }
        return skyscannerService;
    }

    public List<AirportDto> getAirportDtoList() {
        return airportDtoList;
    }

    public void setAirportDtoList(String airport) {
        this.airportDtoList = skyscannerClient.getAirports(airport);
    }

    public BestFlightDto getBestFlightDto() {
        return bestFlightDto;
    }

    public void setBestFlightDto(SearchCriteriaDto searchCriteriaDto) {
        this.bestFlightDto = BestFlightDto.builder().itineraries(ItineraryDto.builder().optionsDto(new ArrayList<>()).build()).build();
        if(!validateSearchCriteriaDto(searchCriteriaDto)) {

            return;

        } else {
            BestFlightDto bestFlightDto = skyscannerClient.getBestFlights(searchCriteriaDto);

            if (bestFlightDto.getItineraries() == null || Optional.ofNullable(bestFlightDto.getItineraries().getOptionsDto()).isEmpty()) {
                this.bestFlightDto = BestFlightDto.builder().itineraries(ItineraryDto.builder().optionsDto(new ArrayList<>()).build()).build();

            } else {
                this.bestFlightDto = bestFlightDto;
            }
        }
    }

    public List<LocationRentACarDto> getLocationRentACarDtoList() {
        return locationRentACarDtoList;
    }

    public void setLocationRentACarDto(String location) {
        this.locationRentACarDtoList = skyscannerClient.getLocationsRentACar(location);
    }

    public RentACarResponseDto getRentACarResponseDto() {
        return rentACarResponseDto;
    }

    public void setRentACarResponseDto(RentACarParameterDto rentACarParameterDto) {
        this.rentACarResponseDto = skyscannerClient.getRentACar(rentACarParameterDto);
    }

    private boolean validateSearchCriteriaDto(SearchCriteriaDto searchCriteriaDto) {
        if(!Optional.ofNullable(searchCriteriaDto.getOriginCountry()).isPresent() ||
                !Optional.ofNullable(searchCriteriaDto.getOriginAirport()).isPresent() ||
                !Optional.ofNullable(searchCriteriaDto.getDepartureDate()).isPresent() ||
                !Optional.ofNullable(searchCriteriaDto.getDestinationCountry()).isPresent() ||
                !Optional.ofNullable(searchCriteriaDto.getDestinationAirport()).isPresent() ||
                !Optional.ofNullable(searchCriteriaDto.getReturnDate()).isPresent()) {
            Notification.show("All firlds needs to be filled in");
            return false;
        } else {
            return true;
        }
    };
}
