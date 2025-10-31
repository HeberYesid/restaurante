package com.example.patterns.structural.bridge;

/**
 * Patrón Bridge - Abstracción
 * Define diferentes tipos de notificaciones del restaurante
 */
public abstract class Notification {
    protected NotificationSender sender;

    public Notification(NotificationSender sender) {
        this.sender = sender;
    }

    public abstract void notify(String recipient);

    public void changeSender(NotificationSender newSender) {
        this.sender = newSender;
    }
}
