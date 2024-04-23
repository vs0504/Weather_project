package com.weather.management.repository;

import com.weather.management.model.LocationDetails;
import com.weather.management.model.WeatherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherDetails,Long> {
    Optional<List<WeatherDetails>> findByLocationDetails(LocationDetails locationDetails);
}
