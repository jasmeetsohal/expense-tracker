package com.xalts.expensetracker.service.impl;

import com.xalts.expensetracker.model.User;
import com.xalts.expensetracker.repository.UserRepository;
import com.xalts.expensetracker.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementation of UserService interface.
 * Handles user-related business logic and operations.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user in the system.
     * @param user The user to register
     * @return The registered user
     * @throws RuntimeException if email already exists
     */
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Find a user by their email address.
     * @param email The email address to search for
     * @return Optional containing the user if found
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get the currently authenticated user.
     * @return The current user
     * @throws RuntimeException if user not found
     */
    @Override
    public User getCurrentUser() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
} 