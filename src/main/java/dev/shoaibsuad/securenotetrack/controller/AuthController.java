package dev.shoaibsuad.securenotetrack.controller;

import dev.shoaibsuad.securenotetrack.entity.User;
import dev.shoaibsuad.securenotetrack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/register")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/user")
    public Map<String, String> registerUser(@Valid @RequestBody User user) {
        String result = userService.registerUser(user, "user");
        return Map.of("message", result);
    }

    @PostMapping("/admin")
    public Map<String, String> registerAdmin(@Valid @RequestBody User user) {
        String result = userService.registerUser(user, "admin");
        return Map.of("message", result);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
