package com.ostad.securenote.controller;

import com.ostad.securenote.entity.User;
import com.ostad.securenote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/register")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/user")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user, "ROLE_USER");
        return "User registered successfully";
    }

    @PostMapping("/admin")
    public String registerAdmin(@RequestBody User user) {
        userService.registerUser(user, "ROLE_ADMIN");
        return "Admin registered successfully";
    }
}