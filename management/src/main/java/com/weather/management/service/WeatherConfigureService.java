package com.weather.management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.management.dto.WeatherConfigureDto;
import org.springframework.http.ResponseEntity;

public interface WeatherConfigureService {
    void scheduleAndSaveWeather(WeatherConfigureDto weatherConfigureDto);

    ResponseEntity<?> getWeatherDetails(Long locationId) throws JsonProcessingException;
}
