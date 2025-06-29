package com.learnwithiftekhar.spring_jwt_demo.controller;

import com.learnwithiftekhar.spring_jwt_demo.dto.LoginRequest;
import com.learnwithiftekhar.spring_jwt_demo.dto.LoginResponse;
import com.learnwithiftekhar.spring_jwt_demo.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest login) {
        // Authenticate the user
        Authentication auth = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                login.getUsername(),
                                login.getPassword()
                        )
                );
        // Generate the token for the authenticated user
        String token = jwtService.generateToken((UserDetails) auth.getPrincipal());
        // Return the token as response
        return new LoginResponse(token);
    }
}
