package com.kodilla.backpacktravelfrontend.service;

import com.kodilla.backpacktravelfrontend.client.CountriesCitiesClient;
import com.kodilla.backpacktravelfrontend.dto.countriesCitiesResponseDto.CountryDto;

import java.util.ArrayList;
import java.util.List;

public class CountriesCitiesService {
    private List<String> countries;
    private static CountriesCitiesService countryService;
    private CountriesCitiesClient countriesCitiesClient = new CountriesCitiesClient();

    private CountriesCitiesService() {
        this.countries = fetchCountries();
    }

    public static CountriesCitiesService getInstance() {
        if (countryService == null) {
            countryService = new CountriesCitiesService();
        }
        return countryService;
    }

    public List<String> getCountries() {
        return countries;
    }

    private List<String> fetchCountries() {
        CountryDto countryDto = countriesCitiesClient.getCountries();
        List<String> countries = new ArrayList<>(countryDto.getCountries().values());
        return countries;
    }
}
