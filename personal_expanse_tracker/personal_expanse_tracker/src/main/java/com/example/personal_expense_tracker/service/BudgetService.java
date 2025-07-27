package com.example.personal_expense_tracker.service;

import java.util.List;

import com.example.personal_expense_tracker.entity.Budget;

public interface BudgetService {
    Budget createBudget(Budget budget);
    List<Budget> getAllBudgets();
}
