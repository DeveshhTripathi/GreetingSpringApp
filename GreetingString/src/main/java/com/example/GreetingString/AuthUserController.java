package com.example.GreetingString;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for User Authentication")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a user and sends a welcome email.")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    public String registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return authenticationService.registerUser(authUserDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Validates credentials and allows login")
    @ApiResponse(responseCode = "200", description = "Login successful")
    public String loginUser(@Valid @RequestBody org.example.greetingspring.LoginDTO loginDTO) {
        return authenticationService.loginUser(loginDTO);
    }
}

