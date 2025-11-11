package com.example.patterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSSender implements NotificationSender {
    private static final Logger logger = LoggerFactory.getLogger(SMSSender.class);
    
    @Override
    public void send(String recipient, String message) {
        logger.info("Enviando SMS al número: {}", recipient);
        logger.info("Texto: {}", message);
        logger.info("---");
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
