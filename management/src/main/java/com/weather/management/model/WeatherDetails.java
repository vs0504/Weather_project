package com.weather.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Entity
@Table(name="weather_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude( JsonInclude.Include.NON_EMPTY)
public class WeatherDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weather_id")
    private Long id;


    @Column(columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String weatherData;

    @Column
    private Date createddate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "usr_location_id")
    private LocationDetails locationDetails;


    private transient Map weatherDetails;

    public WeatherDetails(String weatherData, LocationDetails locationDetails , Date createddate) {
        this.weatherData = weatherData;
        this.locationDetails = locationDetails;
        this.createddate = createddate;
    }
}
