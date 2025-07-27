package com.example.personal_expense_tracker.controller;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.personal_expense_tracker.entity.Expense;
import com.example.personal_expense_tracker.repository.ExpenseRepository;
import com.example.personal_expense_tracker.service.ExpenseService;
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;


    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(String email) {
    return ResponseEntity.ok(expenseService.getAllExpenses(email));
}
   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> filterExpenses(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(expenseService.filterExpenses(start, end));
    }

    // ✅ Weekly Chart Summary (7 days)
    @GetMapping("/weekly-summary")
    public ResponseEntity<Map<String, Double>> getWeeklySummary() {
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(6);

        List<Expense> expenses = expenseRepository.findByDateBetween(weekStart, today);

        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStart.plusDays(i);
            result.put(date.getDayOfWeek().toString(), 0.0);
        }

        for (Expense e : expenses) {
            String day = e.getDate().getDayOfWeek().toString();
            result.put(day, result.getOrDefault(day, 0.0) + e.getAmount());
        }

        return ResponseEntity.ok(result);
    }

    // ✅ Monthly Chart Summary (current month)
    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<String, Double>> getMonthlySummary() {
        LocalDate today = LocalDate.now();
        LocalDate monthStart = today.withDayOfMonth(1);

        List<Expense> expenses = expenseRepository.findByDateBetween(monthStart, today);

        Map<String, Double> result = new LinkedHashMap<>();
        for (Expense e : expenses) {
            String day = e.getDate().toString();
            result.put(day, result.getOrDefault(day, 0.0) + e.getAmount());
        }

        return ResponseEntity.ok(result);
    }

    // ✅ Yearly Chart Summary (month-wise)
    @GetMapping("/yearly-summary")
    public ResponseEntity<Map<String, Double>> getYearlySummary() {
        LocalDate today = LocalDate.now();
        LocalDate yearStart = today.withDayOfYear(1);

        List<Expense> expenses = expenseRepository.findByDateBetween(yearStart, today);

        Map<String, Double> result = new LinkedHashMap<>();
        for (Expense e : expenses) {
            String month = e.getDate().getMonth().toString();
            result.put(month, result.getOrDefault(month, 0.0) + e.getAmount());
        }

        return ResponseEntity.ok(result);
    }
    @GetMapping("/weekly-transactions")
public List<Expense> getWeeklyTransactions() {
    LocalDate today = LocalDate.now();
    LocalDate weekStart = today.minusDays(6);
    return expenseRepository.findByDateBetween(weekStart, today);
}
@GetMapping("/monthly-transactions")
public List<Expense> getMonthlyTransactions() {
    LocalDate today = LocalDate.now();
    LocalDate startOfMonth = today.withDayOfMonth(1);
    return expenseRepository.findByDateBetween(startOfMonth, today);
}
 @GetMapping("/yearly-transactions")
    public List<Expense> getYearlyTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate yearStart = today.withDayOfYear(1);
        return expenseRepository.findByDateBetween(yearStart, today);
    }


}
