package com.example.patterns.structural.bridge;

public class SMSSender implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Enviando SMS al número: " + recipient);
        System.out.println("Texto: " + message);
        System.out.println("---");
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
