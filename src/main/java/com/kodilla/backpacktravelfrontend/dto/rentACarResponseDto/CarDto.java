package com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {

    @JsonProperty("car_name")
    private String car_name;

    @JsonProperty("ac")
    private boolean ac;

    @JsonProperty("quotes_count")
    private int quotes_count;

    @JsonProperty("img")
    private String img;

    @JsonProperty("max_score")
    private double max_score;

    @JsonProperty("max_seats")
    private int max_seats;

    @JsonProperty("min_price")
    private BigDecimal min_price;

    @JsonProperty("mean_price")
    private BigDecimal mean_price;

    @JsonProperty("doors")
    private String doors;

    @JsonProperty("max_bags")
    private int max_bags;

    @JsonProperty("trans")
    private String trans;

    @JsonProperty("fair_fuel")
    private boolean fair_fuel;

    @JsonProperty("cls")
    private String cls;

    @JsonProperty("pickup_method")
    private String pickup_method;
}
