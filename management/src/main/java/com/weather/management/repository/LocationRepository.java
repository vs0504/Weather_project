package com.weather.management.repository;

import com.weather.management.model.LocationDetails;
import com.weather.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationDetails,Long> {
    Optional<List<LocationDetails>> findByUserDetails(User user);
}
