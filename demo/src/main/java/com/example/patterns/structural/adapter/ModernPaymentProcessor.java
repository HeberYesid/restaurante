package com.example.patterns.structural.adapter;

/**
 * Interfaz moderna de procesamiento de pagos
 */
public interface ModernPaymentProcessor {
    PaymentResult processPayment(PaymentRequest request);
    boolean validatePaymentMethod(PaymentRequest request);
}
