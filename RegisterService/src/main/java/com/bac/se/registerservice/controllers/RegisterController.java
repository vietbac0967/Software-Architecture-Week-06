package com.bac.se.registerservice.controllers;

import com.bac.se.registerservice.models.User;
import com.bac.se.registerservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/")
    public User getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userRepository.getUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Not found user"));
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
    }
}
