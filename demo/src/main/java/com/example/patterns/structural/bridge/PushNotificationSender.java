package com.example.patterns.structural.bridge;

public class PushNotificationSender implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Enviando NOTIFICACIÓN PUSH al dispositivo: " + recipient);
        System.out.println("Contenido: " + message);
        System.out.println("---");
    }

    @Override
    public String getType() {
        return "Push Notification";
    }
}
