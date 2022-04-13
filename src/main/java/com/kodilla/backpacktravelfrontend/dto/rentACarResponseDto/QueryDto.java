package com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDto {

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("do_dt")
    private LocalDateTime do_dt;

    @JsonProperty("ccy")
    private String ccy;

    @JsonProperty("age")
    private int age;

    @JsonProperty("pu_dt")
    private LocalDateTime pu_dt;

    @JsonProperty("ucy")
    private String ucy;
}
