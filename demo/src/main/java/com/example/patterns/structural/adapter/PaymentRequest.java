package com.example.patterns.structural.adapter;

import java.time.LocalDateTime;

public class PaymentRequest {
    private String cardNumber;
    private double amount;
    private LocalDateTime timestamp;
    private String customerName;

    public PaymentRequest(String cardNumber, double amount, String customerName) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.customerName = customerName;
        this.timestamp = LocalDateTime.now();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCustomerName() {
        return customerName;
    }
}
