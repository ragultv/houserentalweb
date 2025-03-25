package com.houserental.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Payment {
    private LocalDate date;
    private double amount;
    public LocalDate getDate() {
        return date;
    }
    public Payment(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }

    // Setter for amount
    public void setAmount(double amount) {
        this.amount = amount;
    }
}