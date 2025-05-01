package com.xalts.expensetracker.service;

import com.xalts.expensetracker.model.User;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
    User getCurrentUser();
} 