package com.example.personal_expense_tracker.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.personal_expense_tracker.service.ReminderService;

@Component
public class ReminderScheduler {

    private final ReminderService reminderService;

    public ReminderScheduler(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void runDailyReminderJob() {
        reminderService.sendDueReminders();
    }
}
