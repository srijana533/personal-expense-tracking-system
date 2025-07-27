package com.example.personal_expense_tracker.service;

import java.util.Map;

public interface SummaryService {
    Map<String, Double> getWeeklyStats();
    Map<String, Double> getMonthlyStats();
    Map<String, Double> getYearlyStats();
}
