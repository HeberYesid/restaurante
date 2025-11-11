package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentSystem {
    private static final Logger logger = LoggerFactory.getLogger(PaymentSystem.class);
    
    public boolean processPayment(double amount, String method) {
        logger.info("Procesando pago de ${} mediante {}", amount, method);
        return true;
    }

    public void generateReceipt(String orderId, double amount) {
        logger.info("Generando recibo para orden {} por ${}", orderId, amount);
    }
}
