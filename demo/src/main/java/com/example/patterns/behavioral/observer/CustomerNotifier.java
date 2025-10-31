package com.example.patterns.behavioral.observer;

public class CustomerNotifier implements OrderObserver {
    private String customerName;

    public CustomerNotifier(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String orderId, String status, String message) {
        System.out.println("[NOTIFICACIÓN AL CLIENTE " + customerName + "]");
        System.out.println("  Orden: " + orderId);
        System.out.println("  Estado: " + status);
        System.out.println("  Mensaje: " + message);
    }

    @Override
    public String getObserverName() {
        return "Cliente: " + customerName;
    }
}
