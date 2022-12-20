package com.nortal.test.restassured.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreedsList {
    private List<Breed> data;
    private Integer current_page;
    private Integer last_page;
}
