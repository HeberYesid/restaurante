package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliverySystem {
    private static final Logger logger = LoggerFactory.getLogger(DeliverySystem.class);
    
    public void scheduleDelivery(String orderId, String address) {
        logger.info("Programando entrega para orden {} a {}", orderId, address);
    }

    public void assignDriver(String orderId) {
        logger.info("Asignando conductor para orden {}", orderId);
    }
}
