package com.example.personal_expense_tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfig {
    // Enables scheduled tasks (like ReminderScheduler)
}
