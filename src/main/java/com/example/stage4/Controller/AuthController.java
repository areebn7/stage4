package com.example.stage4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.stage4.Security.TokenService;

@RestController
@RequestMapping("/account")
public class AuthController {

      @Autowired
    TokenService tokenService;

    @GetMapping
    public String health() {
        return "Authorization Server is running.";
    }

    @GetMapping("/register")
    public String register() {
        return "Success.";
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

}
