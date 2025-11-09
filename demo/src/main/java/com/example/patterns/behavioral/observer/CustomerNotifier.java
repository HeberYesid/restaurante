package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNotifier implements OrderObserver {
    private static final Logger logger = LoggerFactory.getLogger(CustomerNotifier.class);
    private String customerName;

    public CustomerNotifier(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String orderId, String status, String message) {
        logger.info("[NOTIFICACIÓN AL CLIENTE {}]", customerName);
        logger.info("  Orden: {}", orderId);
        logger.info("  Estado: {}", status);
        logger.info("  Mensaje: {}", message);
    }

    @Override
    public String getObserverName() {
        return "Cliente: " + customerName;
    }
}
