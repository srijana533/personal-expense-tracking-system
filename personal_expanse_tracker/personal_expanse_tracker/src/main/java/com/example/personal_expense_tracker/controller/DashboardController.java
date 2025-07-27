package com.example.personal_expense_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.personal_expense_tracker.service.SummaryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final SummaryService summaryService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("weeklyData", summaryService.getWeeklyStats());
        model.addAttribute("monthlyData", summaryService.getMonthlyStats());
        model.addAttribute("yearlyData", summaryService.getYearlyStats());
        return "dashboard"; // refers to dashboard.html
    }
}
