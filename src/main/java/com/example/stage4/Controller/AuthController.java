package com.example.stage4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.stage4.Security.TokenService;

/**
 * Controller for handling authentication-related endpoints.
 * Provides endpoints for checking server health and generating JWT tokens.
 */
@RestController
@RequestMapping("/account")
public class AuthController {

    @Autowired
    TokenService tokenService;

    /**
     * Endpoint to check the health of the authorization server.
     * 
     * @return A string indicating that the authorization server is running.
     */
    @GetMapping
    public String health() {
        return "Authorization Server is running.";
    }

    /**
     * Endpoint to generate a JWT token for authenticated users.
     * 
     * @param authentication The authentication object containing user details.
     * @return A JWT token as a string.
     */
    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

}
