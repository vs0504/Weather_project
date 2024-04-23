package com.weather.management.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.management.dto.WeatherConfigureDto;
import com.weather.management.model.LocationDetails;
import com.weather.management.model.WeatherDetails;
import com.weather.management.repository.LocationRepository;
import com.weather.management.repository.WeatherRepository;
import com.weather.management.util.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherConfigureServiceImpl implements WeatherConfigureService{


    @Autowired
    private final TaskScheduler taskScheduler;

    private final RestTemplate restTemplate;

    private final WeatherRepository weatherRepository;

    private final LocationRepository locationRepository;

    public String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=a3833607e41aa45fbc2ed4ee536df98a";
    @Override
    public void scheduleAndSaveWeather(WeatherConfigureDto weatherConfigureDto) {
        String cronExpression= String.format("*/%s * * * * *",weatherConfigureDto.getInterval());
        taskScheduler.schedule(()->pullWeatherAndSavedata(weatherConfigureDto),new CronTrigger(cronExpression));
    }

    @Override
    public ResponseEntity<?> getWeatherDetails(Long locationId) throws JsonProcessingException {
        LocationDetails locationDetails = locationRepository.findById(locationId).get();
        ObjectMapper mapper = new ObjectMapper();
        List<WeatherDetails> weatherDetails = new ArrayList<>();
        Optional<List<WeatherDetails>> optionalWeatherDetailsList = weatherRepository.findByLocationDetails(locationDetails);
        for(WeatherDetails wd:optionalWeatherDetailsList.get()){
            wd.setWeatherDetails(mapper.readValue(wd.getWeatherData(), Map.class));
            wd.setWeatherData("");
            weatherDetails.add(wd);
        }
        return new ResponseEntity<>(new ResponseObject(200,"Fetch Details","Details fetched from database",weatherDetails), HttpStatus.OK);
    }

    private void pullWeatherAndSavedata(WeatherConfigureDto weatherConfigureDto) {
        Optional<LocationDetails>  optionalLocationDetails = locationRepository.findById(weatherConfigureDto.getLocationId());
        LocationDetails locationDetails = optionalLocationDetails.get();
        String apiUrl = String.format(weatherApiUrl,locationDetails.getLatitude(),locationDetails.getLongitude());
        String response = restTemplate.getForObject(apiUrl, String.class);
        WeatherDetails weatherDetails = new WeatherDetails(response.toString(),locationDetails, new Date());
        weatherRepository.save(weatherDetails);

    }
}
