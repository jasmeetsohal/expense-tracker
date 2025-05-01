package com.xalts.expensetracker.controller;

import com.xalts.expensetracker.model.User;
import com.xalts.expensetracker.security.JwtTokenProvider;
import com.xalts.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling authentication-related operations.
 * Provides endpoints for user registration and login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    /**
     * Register a new user.
     * @param user The user to register
     * @return ResponseEntity containing the registered user
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Authenticate a user and generate a JWT token.
     * @param loginRequest Map containing email and password
     * @return ResponseEntity containing the JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.get("email"),
                loginRequest.get("password")
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }
} 