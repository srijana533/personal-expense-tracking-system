package com.example.personal_expense_tracker.dto;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseDTO {

    private String description;
    private double amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Long budgetId;
    private String category;
    private String customCategory;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }

public String getCustomCategory() {
    return customCategory;
}

public void setCustomCategory(String CustomCategory) {
    this.customCategory = CustomCategory;
}
    
}
