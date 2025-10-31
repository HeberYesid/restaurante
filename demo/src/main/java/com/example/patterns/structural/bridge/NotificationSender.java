package com.example.patterns.structural.bridge;

/**
 * Patrón Bridge - Implementación
 * Define la interfaz para los métodos de notificación
 */
public interface NotificationSender {
    void send(String recipient, String message);
    String getType();
}
