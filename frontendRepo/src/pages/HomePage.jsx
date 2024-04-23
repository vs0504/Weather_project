import React, { useEffect, useState } from "react";
import { Button, Typography, Grid } from "@mui/material";
import WeatherConfigurableComponent from "../components/WeatherConfigurableComponent";
import {
  fetchLocations,
  saveLocationDetails,
} from "../apiSection/urlConstants";
import service from "../apiSection/service";
import WeatherHistory from "../components/WeatherHistory";

export default function HomePage(props) {
  const [location, setLocation] = useState(null);
  const [locationOptions, setLocationOptions] = useState([]);

  useEffect(() => {
    getLocationOptions();
  }, []);

  const getLocationOptions = () => {
    service
      .get(fetchLocations + localStorage.getItem("userId"))
      .then((response) => {
        setLocationOptions(response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching locations:", error);
      });
  };

  const getLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          setLocation({ latitude, longitude });
        },
        (error) => {
          console.error("Error getting location:", error);
        }
      );
    } else {
      console.error("Geolocation is not supported by this browser.");
    }
  };

  const saveLocation = () => {
    let locationData = {
      userId: localStorage.getItem("userId"),
      latitude: location.latitude,
      longitude: location.longitude,
    };
    service
      .create(saveLocationDetails, locationData)
      .then((response) => {
        console.log("Data saved successfully:", response.data);
        getLocationOptions();
      })
      .catch((error) => {
        console.error("Error saving data:", error);
      });

    console.log("Saving location:", location);
  };

  return (
    <div>
      <Grid container spacing={2} justify="center" alignItems="center">
        <Grid item xs={12}>
          <Typography variant="h4" align="center" gutterBottom>
            Weather Tarcker
          </Typography>
        </Grid>
        <Grid item xs={12}>
          {location && (
            <Typography variant="body1" align="center">
              Latitude: {location.latitude}, Longitude: {location.longitude}
            </Typography>
          )}
        </Grid>

        {location ? (
          <Grid item xs={12} align="center">
            <Button
              variant="contained"
              color="secondary"
              onClick={saveLocation}
            >
              Save Location
            </Button>
          </Grid>
        ) : (
          <Grid item xs={12} align="center">
            <Button variant="contained" color="primary" onClick={getLocation}>
              Add Location
            </Button>
          </Grid>
        )}
      </Grid>
      {locationOptions.length > 0 && (
        <>
          <br></br>
          <WeatherConfigurableComponent locationOptions={locationOptions} />
          <WeatherHistory locationOptions={locationOptions} />
        </>
      )}
    </div>
  );
}
