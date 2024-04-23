import React, { useState, useEffect } from "react";
import {
  TextField,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  CircularProgress,
  Typography,
  Box,
} from "@mui/material";
import service from "../apiSection/service";
import {
  configureLocationInterval,
  fetchLocations,
} from "../apiSection/urlConstants";

export default function WeatherConfigurableComponent(props) {
  const [location, setLocation] = useState(-1);
  const [interval, setInterval] = useState("");
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState(null);
  const [showLocationList, setShowLocationList] = useState(false);

  const handleSave = () => {
    if (isNaN(interval) || interval <= 0) {
      setError("Interval must be a positive number");
      return;
    }

    setSaving(true);
    setError(null);

    let configureLocationData = {
      locationId: location,
      interval: interval,
    };

    service
      .create(configureLocationInterval, configureLocationData)
      .then((response) => {
        console.log("Data saved successfully:", response.data);
        setShowLocationList(false);
        setSaving(false);
      })
      .catch((error) => {
        console.error("Error saving data:", error);
        setError("Error saving data. Please try again later.");
        setSaving(false);
      });
  };

  const handleShowLocationList = () => {
    setInterval("");
    setLocation(-1);
    setShowLocationList(true);
  };

  return (
    <Box sx={{ maxWidth: 400, margin: "auto" }}>
      <Typography variant="h6" align="center" gutterBottom>
        Set Weather Update Interval
      </Typography>
      {showLocationList ? (
        <div>
          <FormControl fullWidth sx={{ marginBottom: 2 }}>
            <InputLabel id="location-select-label">Select Location</InputLabel>
            <Select
              labelId="location-select-label"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
            >
              <MenuItem key={-1} value={-1}>
                Select location
              </MenuItem>
              {props.locationOptions.map((option) => (
                <MenuItem key={option.id} value={option.id}>
                  {option.latitude + ", " + option.longitude}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <TextField
            fullWidth
            label="Interval (in minutes)"
            type="number"
            value={interval}
            onChange={(e) => setInterval(e.target.value)}
            error={error !== null}
            helperText={error}
            sx={{ marginBottom: 2 }}
          />
        </div>
      ) : null}

      <Button
        variant="contained"
        onClick={showLocationList ? handleSave : handleShowLocationList}
        disabled={
          saving || (showLocationList && (location === -1 || interval === ""))
        }
        fullWidth
        sx={{ marginBottom: 2 }}
      >
        {saving ? (
          <CircularProgress size={24} />
        ) : showLocationList ? (
          "Save"
        ) : (
          "Set weather polling interval"
        )}
      </Button>

      {showLocationList && (
        <Button onClick={() => setShowLocationList(false)} fullWidth>
          Back
        </Button>
      )}
    </Box>
  );
}
