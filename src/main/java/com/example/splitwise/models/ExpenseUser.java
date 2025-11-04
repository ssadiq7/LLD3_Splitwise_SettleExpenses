package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "expense_users")
public class ExpenseUser extends BaseModel {
    @ManyToOne(fetch = FetchType.EAGER)
    private Expense expense;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}
