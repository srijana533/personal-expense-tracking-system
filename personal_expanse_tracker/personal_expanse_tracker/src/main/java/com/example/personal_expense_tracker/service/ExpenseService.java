package com.example.personal_expense_tracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.personal_expense_tracker.dto.ExpenseDTO;
import com.example.personal_expense_tracker.entity.Expense;

public interface ExpenseService {
    Expense createExpense(ExpenseDTO dto, String email);
    Expense getExpenseById(Long id);
    List<Expense> getAllExpenses(String email);
    List<Expense> filterExpenses(LocalDate start, LocalDate end);
    Expense updateExpense(Long id, ExpenseDTO dto);
    void deleteExpense(Long id);

    Map<String, Double> getWeeklySummary();
    Map<String, Double> getMonthlySummary();
    Map<String, Double> getYearlySummary();
}
