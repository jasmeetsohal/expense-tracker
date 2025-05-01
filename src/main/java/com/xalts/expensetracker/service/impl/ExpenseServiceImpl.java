package com.xalts.expensetracker.service.impl;

import com.xalts.expensetracker.model.Expense;
import com.xalts.expensetracker.model.User;
import com.xalts.expensetracker.repository.ExpenseRepository;
import com.xalts.expensetracker.service.ExpenseService;
import com.xalts.expensetracker.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of ExpenseService interface.
 * Handles expense-related business logic and operations.
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    /**
     * Create a new expense for the current user.
     * @param expense The expense to create
     * @return The created expense
     */
    @Override
    public Expense createExpense(Expense expense) {
        User user = userService.getCurrentUser();
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    /**
     * Update an existing expense.
     * @param id The ID of the expense to update
     * @param expense The updated expense data
     * @return The updated expense
     * @throws RuntimeException if user is not authorized to update the expense
     */
    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        User user = userService.getCurrentUser();
        
        if (!existingExpense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to update this expense");
        }
        
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setDate(expense.getDate());
        
        return expenseRepository.save(existingExpense);
    }

    /**
     * Delete an expense.
     * @param id The ID of the expense to delete
     * @throws RuntimeException if user is not authorized to delete the expense
     */
    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        User user = userService.getCurrentUser();
        
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to delete this expense");
        }
        
        expenseRepository.delete(expense);
    }

    /**
     * Get an expense by its ID.
     * @param id The ID of the expense to retrieve
     * @return The expense
     * @throws RuntimeException if expense not found
     */
    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    /**
     * Get all expenses for the current user with pagination.
     * @param pageable Pagination information
     * @return Page of expenses
     */
    @Override
    public Page<Expense> getUserExpenses(Pageable pageable) {
        User user = userService.getCurrentUser();
        return expenseRepository.findByUser(user, pageable);
    }

    /**
     * Get expenses for the current user within a date range.
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return List of expenses within the date range
     */
    @Override
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        User user = userService.getCurrentUser();
        return expenseRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

    /**
     * Get category-wise expense totals for the current user.
     * @return List of Object arrays containing category and total amount
     */
    @Override
    public List<Object[]> getCategoryWiseExpenses() {
        User user = userService.getCurrentUser();
        return expenseRepository.getCategoryWiseExpenses(user);
    }
} 