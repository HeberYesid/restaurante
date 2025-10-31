package com.example.patterns.structural.adapter;

public class PaymentResult {
    private boolean success;
    private String message;
    private String transactionId;

    public PaymentResult(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return String.format("PaymentResult{success=%s, message='%s', transactionId='%s'}", 
                           success, message, transactionId);
    }
}
