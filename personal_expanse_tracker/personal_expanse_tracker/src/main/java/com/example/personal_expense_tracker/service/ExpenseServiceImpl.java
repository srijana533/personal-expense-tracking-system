package com.example.personal_expense_tracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.personal_expense_tracker.dto.ExpenseDTO;
import com.example.personal_expense_tracker.entity.Expense;
import com.example.personal_expense_tracker.entity.User;
import com.example.personal_expense_tracker.repository.ExpenseRepository;
import com.example.personal_expense_tracker.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

  @Override
public Expense createExpense(ExpenseDTO dto, String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Expense expense = new Expense();
    expense.setDescription(dto.getDescription());
    expense.setAmount(dto.getAmount());
    expense.setDate(dto.getDate());

    if (dto.getCustomCategory() != null && !dto.getCustomCategory().isBlank()) {
        expense.setCategory("Others (Custom)");
        expense.setCustomCategory(dto.getCustomCategory());
    } else {
        expense.setCategory(dto.getCategory());
        expense.setCustomCategory(null);
    }

    expense.setUser(user); // ✅ Associate the user

    return expenseRepository.save(expense);
}

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense not found with ID: " + id));
    }
@Override
public List<Expense> getAllExpenses(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
        return expenseRepository.findByUser(user.get());
    } else {
        return new ArrayList<>();
    }
}

    @Override
    public List<Expense> filterExpenses(LocalDate start, LocalDate end) {
        return expenseRepository.findByDateBetween(start, end);
    }

    @Override
    public Expense updateExpense(Long id, ExpenseDTO dto) {
        Expense expense = getExpenseById(id);
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());

        if (dto.getCustomCategory() != null && !dto.getCustomCategory().isBlank()) {
            expense.setCategory("Others (Custom)");
            expense.setCustomCategory(dto.getCustomCategory());
        } else {
            expense.setCategory(dto.getCategory());
            expense.setCustomCategory(null);
        }

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Map<String, Double> getWeeklySummary() {
        return summarizeExpenses(7);
    }

    @Override
    public Map<String, Double> getMonthlySummary() {
        return summarizeExpenses(30);
    }

    @Override
    public Map<String, Double> getYearlySummary() {
        return summarizeExpenses(365);
    }

    private Map<String, Double> summarizeExpenses(int pastDays) {
        LocalDate today = LocalDate.now();
        LocalDate from = today.minusDays(pastDays);
        List<Expense> recentExpenses = expenseRepository.findByDateBetween(from, today);

        return recentExpenses.stream().collect(Collectors.groupingBy(
                e -> "Others (Custom)".equals(e.getCategory()) && e.getCustomCategory() != null
                        ? e.getCustomCategory()
                        : e.getCategory(),
                Collectors.summingDouble(Expense::getAmount)
        ));
    }

    public Object getWeeklyStats() {
        throw new UnsupportedOperationException("Unimplemented method 'getWeeklyStats'");
    }

    
}
