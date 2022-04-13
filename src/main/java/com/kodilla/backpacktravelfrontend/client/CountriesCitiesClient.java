package com.kodilla.backpacktravelfrontend.client;

import com.kodilla.backpacktravelfrontend.dto.countriesCitiesResponseDto.CityResponseDto;
import com.kodilla.backpacktravelfrontend.dto.countriesCitiesResponseDto.CountryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class CountriesCitiesClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger LOGGER = LoggerFactory.getLogger(CountriesCitiesClient.class);
    private static final String COUNTRIESCITIESAPIENDPOINT = "http://localhost:8080/v1/countries";

    public CountryDto getCountries() {
        URI url = UriComponentsBuilder.fromHttpUrl(COUNTRIESCITIESAPIENDPOINT + "/getCountries")
                .build()
                .encode()
                .toUri();

        try {
            CountryDto response = restTemplate.getForObject(url, CountryDto.class);
            System.out.println("Odpowiedz: " + response);
            return response;

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage());
        }
    }

    public CityResponseDto getCitiesInCountry(String countryCode) {
        URI url = UriComponentsBuilder.fromHttpUrl(COUNTRIESCITIESAPIENDPOINT + "/getCities/" + countryCode)
                .build()
                .encode()
                .toUri();

        try {
            CityResponseDto response = restTemplate.getForObject(url, CityResponseDto.class);
            System.out.println("Odpowiedz: " + response);
            return response;

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage());
        }
    }
}
