package com.kodilla.backpacktravelfrontend.client;

import com.kodilla.backpacktravelfrontend.dto.*;
import com.kodilla.backpacktravelfrontend.dto.airportDto.AirportDto;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.BestFlightDto;
import com.kodilla.backpacktravelfrontend.dto.locationRentACarDto.LocationRentACarDto;
import com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto.RentACarResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

public class SkyscannerClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger LOGGER = LoggerFactory.getLogger(SkyscannerClient.class);
    private static final String SKYSCANNERAPIENDPOINT = "http://localhost:8080/v1/skyscanner";

    public List<AirportDto> getAirports(String airport) {
        URI url = UriComponentsBuilder.fromHttpUrl(SKYSCANNERAPIENDPOINT + "/getAirports/" + airport)
                .build()
                .encode()
                .toUri();

        try {
            AirportDto[] response = restTemplate.getForObject(url, AirportDto[].class);
            return new ArrayList<>(Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public BestFlightDto getBestFlights(SearchCriteriaDto searchCriteriaDto) {
        StringBuilder flightDetails = new StringBuilder();
        flightDetails.append("adults=").append(searchCriteriaDto.getPassengers());
        flightDetails.append("&origin=").append(searchCriteriaDto.getOriginAirport().getIata_code());
        flightDetails.append("&destination=").append(searchCriteriaDto.getDestinationAirport().getIata_code());
        flightDetails.append("&departureDate=").append(searchCriteriaDto.getDepartureDate());
        flightDetails.append("&currency=").append(searchCriteriaDto.getCurrency());

        if(searchCriteriaDto.getWay().equals("With return flight")) {
            flightDetails.append("&returnDate=").append(searchCriteriaDto.getReturnDate());
        }

        URI url = UriComponentsBuilder.fromHttpUrl(SKYSCANNERAPIENDPOINT + "/getBestFlight/" + flightDetails)
                .build()
                .encode()
                .toUri();

        try {
            BestFlightDto response = restTemplate.getForObject(url, BestFlightDto.class);
            return response;

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage());
        }
    }

    public List<LocationRentACarDto> getLocationsRentACar(String location) {
        URI url = UriComponentsBuilder.fromHttpUrl(SKYSCANNERAPIENDPOINT + "/getLocationsRentACar/" + location)
                .build()
                .encode()
                .toUri();

        try {
            LocationRentACarDto[] response = restTemplate.getForObject(url, LocationRentACarDto[].class);
            return new ArrayList<>(Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public RentACarResponseDto getRentACar(RentACarParameterDto rentACarParameterDto) {
        StringBuilder rentACarDetails = new StringBuilder();
        rentACarDetails.append("pickupId=").append(rentACarParameterDto.getPickupId());
        rentACarDetails.append("&pickupDate=").append(rentACarParameterDto.getPickupDate());
        rentACarDetails.append("&pickupTime=").append(rentACarParameterDto.getPickupTime());
        rentACarDetails.append("&returnDate=").append(rentACarParameterDto.getReturnDate());
        rentACarDetails.append("&returnTime=").append(rentACarParameterDto.getReturnTime());
        rentACarDetails.append("&currency=").append(rentACarParameterDto.getCurrency());

        URI url = UriComponentsBuilder.fromHttpUrl(SKYSCANNERAPIENDPOINT + "/getHotel/" + rentACarDetails)
                .build()
                .encode()
                .toUri();

        try {
            RentACarResponseDto response = restTemplate.getForObject(url, RentACarResponseDto.class);
            return response;

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage());
        }
    }
}
