package com.weather.management.dto;

import lombok.Data;

@Data
public class WeatherConfigureDto {
    private Long locationId;
    private Long interval;
}
