package com.xalts.expensetracker.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * Expense entity representing a user's expense record.
 * Contains expense details and is associated with a user.
 */
@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Amount of the expense */
    @Column(nullable = false)
    private double amount;

    /** Optional description of the expense */
    private String description;

    /** Category of the expense (e.g., Food, Travel, Entertainment) */
    @Column(nullable = false)
    private String category;

    /** Date when the expense was made (defaults to current date) */
    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    /** User who made this expense */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
} 