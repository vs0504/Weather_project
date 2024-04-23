import React, { useState } from "react";
import { TextField, Button, Container, Typography } from "@mui/material";
import { loginUrl } from "../apiSection/urlConstants";
import service from "../apiSection/service";

export default function Login(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    console.log("props--->", props);
    console.log("Username:", username);
    console.log("Passwords:", password);
    let loginData = {
      username: username,
      password: password,
    };
    service
      .create(loginUrl, loginData)
      .then((response) => {
        console.log("Data saved successfully:", response.data);
        localStorage.setItem("userId", response.data.data.id);
      })
      .catch((error) => {
        console.error("Error saving data:", error);
      });
    props.RedirectPath("/weatherTracker");
  };

  return (
    <Container maxWidth="xs">
      <div style={{ marginTop: "100px", textAlign: "center" }}>
        <Typography variant="h4" gutterBottom>
          Login
        </Typography>

        <TextField
          label="Username"
          variant="outlined"
          fullWidth
          margin="normal"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <TextField
          label="Password"
          variant="outlined"
          fullWidth
          margin="normal"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <Button
          variant="contained"
          color="primary"
          type="submit"
          onClick={handleLogin}
        >
          Login
        </Button>
      </div>
    </Container>
  );
}
