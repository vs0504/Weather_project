package com.weather.management.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.management.dto.WeatherConfigureDto;
import com.weather.management.service.WeatherConfigureService;
import com.weather.management.util.ResponseObject;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/weatherConfigurable")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherConfigurableController {

    private final WeatherConfigureService weatherConfigureService;

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleWeatherData(@RequestBody WeatherConfigureDto weatherConfigureDto){
        weatherConfigureService.scheduleAndSaveWeather(weatherConfigureDto);
        return  new ResponseEntity<>((new ResponseObject(200,"success","started successfully","Started pulling data from weather api")), HttpStatus.OK);
    }

    @GetMapping("/fetchWeatherHistory")
    public ResponseEntity<?> getWeatherDetails(@RequestParam Long locationId) throws JsonProcessingException {
       return weatherConfigureService.getWeatherDetails(locationId);
    }
}
