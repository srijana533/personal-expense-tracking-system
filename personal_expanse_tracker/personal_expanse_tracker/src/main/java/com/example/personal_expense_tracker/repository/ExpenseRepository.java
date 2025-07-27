package com.example.personal_expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.personal_expense_tracker.entity.Expense;
import com.example.personal_expense_tracker.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByUserEmail(String email);

    public List<Expense> findByUser(User get);

}
