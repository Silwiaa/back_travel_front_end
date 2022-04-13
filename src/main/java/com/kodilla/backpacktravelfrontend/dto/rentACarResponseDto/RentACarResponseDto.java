package com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentACarResponseDto {

    @JsonProperty("quotes")
    private List<QuotesDto> quotes;

    @JsonProperty("quotes_count")
    private int quotes_count;

    @JsonProperty("groups")
    private Map<String,CarDto> groups;

    @JsonProperty("groups_count")
    private int groups_count;

    @JsonProperty("providers")
    private Map<String, ProviderDto> providers;

    @JsonProperty("query")
    private QueryDto query;

    @JsonProperty("context")
    private Map<String, String> context;
}
