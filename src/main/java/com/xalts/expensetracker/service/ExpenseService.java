package com.xalts.expensetracker.service;

import com.xalts.expensetracker.model.Expense;
import com.xalts.expensetracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    Expense createExpense(Expense expense);
    Expense updateExpense(Long id, Expense expense);
    void deleteExpense(Long id);
    Expense getExpenseById(Long id);
    Page<Expense> getUserExpenses(Pageable pageable);
    List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate);
    List<Object[]> getCategoryWiseExpenses();
} 