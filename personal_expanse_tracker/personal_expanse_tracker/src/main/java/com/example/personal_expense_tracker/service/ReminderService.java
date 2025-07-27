package com.example.personal_expense_tracker.service;

import java.util.List;

import com.example.personal_expense_tracker.entity.Reminder;

public interface ReminderService {
    Reminder createReminder(Reminder reminder);
    List<Reminder> getAllReminders();
    void sendDueReminders();
}
