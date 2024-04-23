package com.weather.management.service;

import com.weather.management.dto.LocationDto;
import com.weather.management.model.LocationDetails;
import com.weather.management.model.User;
import com.weather.management.repository.LocationRepository;
import com.weather.management.repository.UserRepository;
import com.weather.management.util.ResponseObject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationServiceImpl implements LocationService{

    private final UserRepository userRepository;

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public ResponseEntity<?> saveLocation(LocationDto locationDto) {
        try {
            Optional<User> optionalUser =userRepository.findById(locationDto.getUserId());
            LocationDetails locationDetails = new LocationDetails( locationDto.getLatitude(), locationDto.getLongitude(),optionalUser.get(),new Date());
            locationDetails = locationRepository.save(locationDetails);
            return  new ResponseEntity<>(( new ResponseObject(200,"Success","Saved location details",locationDetails)), HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(( new ResponseObject(500,"Failed","",e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<?> getAllLocationDetails(Long userId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            Optional<List<LocationDetails>> optionalLocationDetails = locationRepository.findByUserDetails(optionalUser.get());
            return  new ResponseEntity<>(( new ResponseObject(200,"Success","Fetched location details",optionalLocationDetails.get())), HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(( new ResponseObject(500,"Failed","",e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
