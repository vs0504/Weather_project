package com.weather.management.controller;


import com.weather.management.dto.LoginDto;
import com.weather.management.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto){
        return loginService.authenticateUser(loginDto);
    }

    @GetMapping("/hellos")
    public String getHello(){
        return "Application working";
    }

}
