package com.example.patterns.structural.bridge;

public class EmailSender implements NotificationSender {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Enviando EMAIL a: " + recipient);
        System.out.println("Asunto: Notificación del Restaurante");
        System.out.println("Mensaje: " + message);
        System.out.println("---");
    }

    @Override
    public String getType() {
        return "Email";
    }
}
