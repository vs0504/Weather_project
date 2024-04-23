package com.weather.management.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long userId;
    private Double latitude;
    private Double longitude;
}
