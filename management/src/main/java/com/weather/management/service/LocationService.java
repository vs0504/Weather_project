package com.weather.management.service;

import com.weather.management.dto.LocationDto;
import org.springframework.http.ResponseEntity;

public interface LocationService {
    ResponseEntity<?> saveLocation(LocationDto locationDto);

    ResponseEntity<?> getAllLocationDetails(Long userId);
}
