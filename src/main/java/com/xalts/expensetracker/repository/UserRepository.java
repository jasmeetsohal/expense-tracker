package com.xalts.expensetracker.repository;

import com.xalts.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Provides methods for user-related database operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by their username.
     * @param username The username to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Find a user by their email address.
     * @param email The email address to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists with the given email address.
     * @param email The email address to check
     * @return true if a user exists with the email, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Check if a user exists with the given username.
     * @param username The username to check
     * @return true if a user exists with the username, false otherwise
     */
    boolean existsByUsername(String username);
} 