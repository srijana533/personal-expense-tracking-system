package com.example.personal_expense_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.personal_expense_tracker.entity.Budget;
import com.example.personal_expense_tracker.entity.Expense;
import com.example.personal_expense_tracker.entity.User;
import com.example.personal_expense_tracker.repository.BudgetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
public List<Expense> getAllExpenses(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    return expenseRepository.findByUser(user);
}

}
