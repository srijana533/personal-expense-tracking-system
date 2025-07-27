package com.example.personal_expense_tracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.personal_expense_tracker.entity.Reminder;
import com.example.personal_expense_tracker.repository.ReminderRepository;

@Service
public class ReminderServiceImpl implements ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;
    @Override
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }
    @Override
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }
    @Override
    public void sendDueReminders() {
        List<Reminder> dueReminders = reminderRepository.findByReminderTimeBefore(LocalDateTime.now());
        for (Reminder reminder : dueReminders) {
            System.out.println("🔔 Reminder: " + reminder.getMessage());
        }
    }
}
