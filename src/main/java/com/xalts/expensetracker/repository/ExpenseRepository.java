package com.xalts.expensetracker.repository;

import com.xalts.expensetracker.model.Expense;
import com.xalts.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Expense entity.
 * Provides methods for expense-related database operations.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    /**
     * Find all expenses for a specific user with pagination.
     * @param user The user whose expenses to find
     * @param pageable Pagination information
     * @return Page of expenses
     */
    Page<Expense> findByUser(User user, Pageable pageable);
    
    /**
     * Find expenses for a user within a specific date range.
     * @param user The user whose expenses to find
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return List of expenses within the date range
     */
    @Query("SELECT e FROM Expense e WHERE e.user = :user AND e.date BETWEEN :startDate AND :endDate")
    List<Expense> findByUserAndDateBetween(@Param("user") User user, 
                                          @Param("startDate") LocalDate startDate, 
                                          @Param("endDate") LocalDate endDate);
    
    /**
     * Get total expenses grouped by category for a user.
     * @param user The user whose expenses to analyze
     * @return List of Object arrays containing category and total amount
     */
    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.user = :user GROUP BY e.category")
    List<Object[]> getCategoryWiseExpenses(@Param("user") User user);
} 