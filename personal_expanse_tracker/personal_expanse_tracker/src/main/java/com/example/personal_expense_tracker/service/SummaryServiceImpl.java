package com.example.personal_expense_tracker.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.personal_expense_tracker.entity.Expense;
import com.example.personal_expense_tracker.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final ExpenseRepository repo;

    private Map<String, Double> summarize(List<Expense> expenses) {
        Map<String, Double> summary = new HashMap<>();
        for (Expense e : expenses) {
            String cat = e.getCategory() != null ? e.getCategory() : e.getCustomCategory();
            summary.put(cat, summary.getOrDefault(cat, 0.0) + e.getAmount());
        }
        return summary;
    }

    @Override
    public Map<String, Double> getWeeklyStats() {
        return summarize(repo.findByDateBetween(LocalDate.now().minusDays(6), LocalDate.now()));
    }

    @Override
    public Map<String, Double> getMonthlyStats() {
        return summarize(repo.findByDateBetween(LocalDate.now().minusDays(30), LocalDate.now()));
    }

    @Override
    public Map<String, Double> getYearlyStats() {
        return summarize(repo.findByDateBetween(LocalDate.now().minusDays(365), LocalDate.now()));
    }
}
