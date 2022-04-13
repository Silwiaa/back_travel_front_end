package com.kodilla.backpacktravelfrontend.dto.locationRentACarDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationRentACarDto {

    @JsonProperty("hierarchy")
    private String hierarchy;

    @JsonProperty("location")
    private String location;

    @JsonProperty("entity_name")
    private String entity_name;

    @JsonProperty("highlight")
    private Map<String, String> highlight;

    @JsonProperty("entity_id")
    private String entity_id;

    @JsonProperty("class")
    private String entityClass;
}
