package com.kodilla.backpacktravelfrontend.dto.rentACarResponseDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotesDto {

    @JsonProperty("fuel_pol")
    private String fuel_pol;

    @JsonProperty("sipp")
    private String sipp;

    @JsonProperty("pu_rn_id")
    private int pu_rn_id;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("booking_panel_option_guid")
    private String booking_panel_option_guid;

    @JsonProperty("pickup_type")
    private String pickup_type;

    @JsonProperty("adds")
    private Map<String, Object> adds;

    @JsonProperty("pu")
    private String pu;

    @JsonProperty("score")
    private double score;

    @JsonProperty("vndr_img")
    private String vndr_img;

    @JsonProperty("vndr_img_rounded")
    private String vndr_img_rounded;

    @JsonProperty("vndr")
    private String vndr;

    @JsonProperty("do")
    private String doNo;

    @JsonProperty("bags")
    private int bags;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("prv_id")
    private String prv_id;

    @JsonProperty("car_name")
    private String car_name;

    @JsonProperty("group")
    private String group;

    @JsonProperty("dplnk")
    private String dplnk;

    @JsonProperty("do_rn_id")
    private int do_rn_id;

    @JsonProperty("pickup_method")
    private String pickup_method;
}
