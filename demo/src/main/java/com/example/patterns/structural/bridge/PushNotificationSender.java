package com.example.patterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushNotificationSender implements NotificationSender {
    private static final Logger logger = LoggerFactory.getLogger(PushNotificationSender.class);
    
    @Override
    public void send(String recipient, String message) {
        logger.info("Enviando NOTIFICACIÓN PUSH al dispositivo: {}", recipient);
        logger.info("Contenido: {}", message);
        logger.info("---");
    }

    @Override
    public String getType() {
        return "Push Notification";
    }
}
