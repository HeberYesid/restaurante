package com.example.patterns.structural.adapter;

/**
 * Patrón Adapter
 * Sistema antiguo de punto de venta
 */
public class LegacyPOSSystem {
    public String processPaymentOldFormat(String cardNumber, String amount, String date) {
        return String.format("LEGACY: Procesando pago de %s con tarjeta %s en fecha %s", 
                           amount, maskCardNumber(cardNumber), date);
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }

    public boolean validateCardOldFormat(String cardNumber) {
        return cardNumber != null && cardNumber.length() >= 13;
    }
}
