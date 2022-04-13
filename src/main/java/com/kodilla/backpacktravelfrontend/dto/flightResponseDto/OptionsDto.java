package com.kodilla.backpacktravelfrontend.dto.flightResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.backpacktravelfrontend.dto.flightResponseDto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionsDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("items")
    private List<ItemDto> items;
}
