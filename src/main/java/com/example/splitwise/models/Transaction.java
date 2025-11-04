package com.example.splitwise.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "transactions")
public class Transaction extends BaseModel{
    private User paidFrom;
    private User paidTo;
    private double amount;
}
