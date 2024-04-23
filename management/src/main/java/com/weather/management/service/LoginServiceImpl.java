package com.weather.management.service;

import com.weather.management.dto.LoginDto;
import com.weather.management.model.User;
import com.weather.management.repository.UserRepository;
import com.weather.management.util.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<?> authenticateUser(LoginDto loginDto) {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
            if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>((new ResponseObject(200, "success", "Valid User", optionalUser.get())), HttpStatus.OK);
            } else {
                return new ResponseEntity<>((new ResponseObject(400, "success", "In Valid User", loginDto)), HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>((new ResponseObject(500, "Internal serror", "Server error", e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
