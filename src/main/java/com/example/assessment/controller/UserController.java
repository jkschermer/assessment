package com.example.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assessment.service.UserService;
import com.example.assessment.dto.UserDto;

import java.security.NoSuchAlgorithmException;

import static com.example.assessment.util.Validation.isEmailValid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody UserDto userDto) {
        var checkingValidationMail = isEmailValid(userDto.getUsername());
        ResponseEntity<String> response;

        if (checkingValidationMail) {
            try {
                userService.createAccount(userDto);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            response = ResponseEntity.ok("Account created succesfully");
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid email address");
        }

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAccount(@RequestBody UserDto userDto) {
        try {
            userService.loginAccount(userDto);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
}
