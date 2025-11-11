package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KitchenSystem {
    private static final Logger logger = LoggerFactory.getLogger(KitchenSystem.class);
    
    public void sendOrderToKitchen(String orderDetails) {
        logger.info("Enviando orden a cocina: {}", orderDetails);
    }

    public void notifyOrderReady(String orderId) {
        logger.info("Orden {} lista para servir", orderId);
    }
}
