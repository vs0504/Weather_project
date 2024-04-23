import React, { useEffect, useState } from "react";
import service from "../apiSection/service";
import {
  fetchLocations,
  fetchWeatherHistory,
} from "../apiSection/urlConstants";
import { Line } from "react-chartjs-2";
import { Chart as ChartJS } from "chart.js/auto";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";

export default function WeatherHistory(props) {
  const [weatherData, setWeatherData] = useState([]);
  const [location, setLocation] = useState(1);

  useEffect(() => {
    service
      .get(fetchWeatherHistory + location)
      .then((response) => {
        setWeatherData({});
        const data = response.data.data.map((item) => ({
          x: formatDate(new Date(item.createddate)),
          y: item.weatherDetails.main.temp,
        }));
        setWeatherData(data);
      })
      .catch((error) => {
        console.error("Error fetching weather data:", error);
      });
  }, [location]);

  const chartData = {
    labels: weatherData.map((entry) => entry.x),
    datasets: [
      {
        label: "Temperature",
        data: weatherData.map((entry) => entry.y),
        fill: true,
        borderColor: "rgb(75, 192, 192)",
        tension: 0.1,
      },
    ],
  };
  const chartOptions = {
    scales: {
      y: {
        suggestedMin: 100,
        suggestedMax: 500,
        stepSize: 50,
      },
    },
  };

  const formatDate = (date) => {
    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const year = date.getFullYear();

    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
  };

  return (
    <div style={{ width: "40%", height: "400px", margin: "auto" }}>
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <h2>Weather History</h2>
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
              {props.locationOptions &&
                props.locationOptions.map((option) => (
                  <MenuItem key={option.id} value={option.id}>
                    {option.latitude + ", " + option.longitude}
                  </MenuItem>
                ))}
            </Select>
          </FormControl>
        </div>
      </div>
      <div>
        <Line data={chartData} options={chartOptions} />
      </div>
    </div>
  );
}
