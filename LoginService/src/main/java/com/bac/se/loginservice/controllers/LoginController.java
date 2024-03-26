package com.bac.se.loginservice.controllers;

import com.bac.se.loginservice.models.RequestUser;
import com.bac.se.loginservice.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final String REGISTER_URL = "http://localhost:8088/api/v1/register/";

    @GetMapping("/{id}")
    public User getUserWithId(@PathVariable("id") Long id) {
        String request = REGISTER_URL + "/" + id;
        RestTemplate restTemplate = new RestTemplate();

        final var forObject = restTemplate.getForObject(request, User.class);
        System.out.println(forObject);
        return forObject;
    }

    @GetMapping(value = "/")
    public User getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Validate input parameters
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be null or empty.");
        }
        // Build the request URL with parameters
        String requestUrl = UriComponentsBuilder.fromHttpUrl(REGISTER_URL)
                .queryParam("username", username)
                .queryParam("password", password)
                .toUriString();
        // Create a RestTemplate instance
        System.out.println(requestUrl);
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send GET request and retrieve the User object
            return restTemplate.getForObject(requestUrl, User.class);
        } catch (Exception e) {
            // Handle exceptions
            // Log the error for debugging
            System.err.println("Error occurred while making the HTTP request: " + e.getMessage());
            // You might want to throw custom exception or return an error response here
            return null; // Or handle error case appropriately
        }
    }

    @PostMapping
    public User sendUser(@RequestBody RequestUser requestUser) {
        String loginRequest = "http://localhost:9191/api/v1/login/";
        String requestURL = UriComponentsBuilder.fromHttpUrl(loginRequest)
                .queryParam("username", requestUser.getUsername())
                .queryParam("password", requestUser.getPassword())
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(requestURL, User.class);
    }
}
