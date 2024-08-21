package com.example.stage4.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stage4.JwtUtil;
import com.example.stage4.Model.Account;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public String homePage() {
        return "Welcome to my REST API!";
    }
}
