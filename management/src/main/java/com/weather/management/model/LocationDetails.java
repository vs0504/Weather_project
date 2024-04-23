package com.weather.management.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name="user_location_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_location_id")
    private Long id;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private Date createddate;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "user_id")
    private User userDetails;

    public LocationDetails(Double latitude, Double longitude, User userDetails,Date createddate) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.userDetails = userDetails;
        this.createddate = createddate;
    }
}
