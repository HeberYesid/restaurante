package com.example.patterns.structural.adapter;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Adapter que permite usar el sistema legacy con la interfaz moderna
 */
public class PaymentAdapter implements ModernPaymentProcessor {
    private LegacyPOSSystem legacySystem;

    public PaymentAdapter(LegacyPOSSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        // Convertir formato moderno a formato antiguo
        String cardNumber = request.getCardNumber();
        String amount = String.format("%.2f", request.getAmount());
        String date = request.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // Llamar al sistema legacy
        String legacyResponse = legacySystem.processPaymentOldFormat(cardNumber, amount, date);
        
        // Convertir respuesta legacy a formato moderno
        String transactionId = UUID.randomUUID().toString();
        return new PaymentResult(true, legacyResponse, transactionId);
    }

    @Override
    public boolean validatePaymentMethod(PaymentRequest request) {
        return legacySystem.validateCardOldFormat(request.getCardNumber());
    }
}
