package com.weather.management.controller;


import com.weather.management.dto.LocationDto;
import com.weather.management.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationController {

    private final LocationService locationService;
    @PostMapping("/saveLocation")
    public ResponseEntity<?> saveLocationDetails(@RequestBody LocationDto locationDto){
        return locationService.saveLocation(locationDto);
    }

    @GetMapping("/getLocationBasedOnUserId")
    public ResponseEntity<?> getLocationsBasedOnUserId(@RequestParam Long userId){
        return locationService.getAllLocationDetails(userId);
    }

}
