package com.example.personal_expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.personal_expense_tracker.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

}

