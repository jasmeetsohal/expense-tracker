package com.xalts.expensetracker.controller;

import com.xalts.expensetracker.model.Expense;
import com.xalts.expensetracker.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for handling expense-related operations.
 * Provides endpoints for managing expenses and generating reports.
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * Create a new expense.
     * @param expense The expense to create
     * @return ResponseEntity containing the created expense
     */
    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    /**
     * Update an existing expense.
     * @param id The ID of the expense to update
     * @param expense The updated expense data
     * @return ResponseEntity containing the updated expense
     */
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    /**
     * Delete an expense.
     * @param id The ID of the expense to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get an expense by its ID.
     * @param id The ID of the expense to retrieve
     * @return ResponseEntity containing the expense
     */
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    /**
     * Get all expenses for the current user with pagination.
     * @param pageable Pagination information
     * @return ResponseEntity containing a page of expenses
     */
    @GetMapping
    public ResponseEntity<Page<Expense>> getUserExpenses(Pageable pageable) {
        return ResponseEntity.ok(expenseService.getUserExpenses(pageable));
    }

    /**
     * Get expenses within a date range.
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return ResponseEntity containing a list of expenses
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(expenseService.getExpensesByDateRange(startDate, endDate));
    }

    /**
     * Get category-wise expense totals.
     * @return ResponseEntity containing category-wise expense totals
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Object[]>> getCategoryWiseExpenses() {
        return ResponseEntity.ok(expenseService.getCategoryWiseExpenses());
    }
} 