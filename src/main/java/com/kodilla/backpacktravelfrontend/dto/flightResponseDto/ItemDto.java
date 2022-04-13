package com.kodilla.backpacktravelfrontend.dto.flightResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.LegsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("price")
    private Map<String, String> price;

    @JsonProperty("legs")
    private List<LegsDto> legs;

    @JsonProperty("isSelfTransfer")
    private boolean isSelfTransfer;
}
