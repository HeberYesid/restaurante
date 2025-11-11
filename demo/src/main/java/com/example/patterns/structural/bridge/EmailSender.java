package com.example.patterns.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender implements NotificationSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);
    
    @Override
    public void send(String recipient, String message) {
        logger.info("Enviando EMAIL a: {}", recipient);
        logger.info("Asunto: Notificación del Restaurante");
        logger.info("Mensaje: {}", message);
        logger.info("---");
    }

    @Override
    public String getType() {
        return "Email";
    }
}
