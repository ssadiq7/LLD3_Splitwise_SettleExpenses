package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "transactions")
public class Transaction extends BaseModel{
    @ManyToOne
    private User paidFrom;
    @ManyToOne
    private User paidTo;
    private double amount;
}
