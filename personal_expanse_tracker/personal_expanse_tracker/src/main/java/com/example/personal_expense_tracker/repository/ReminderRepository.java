package com.example.personal_expense_tracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.personal_expense_tracker.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByReminderTimeBefore(LocalDateTime time);
}
