package com.example.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject - Observable
 */
public class OrderTracker {
    private List<OrderObserver> observers = new ArrayList<>();
    private String orderId;
    private String currentStatus;

    public OrderTracker(String orderId) {
        this.orderId = orderId;
        this.currentStatus = "CREADA";
    }

    public void attach(OrderObserver observer) {
        observers.add(observer);
        System.out.println("Observer registrado: " + observer.getObserverName());
    }

    public void detach(OrderObserver observer) {
        observers.remove(observer);
        System.out.println("Observer removido: " + observer.getObserverName());
    }

    public void updateStatus(String newStatus, String message) {
        this.currentStatus = newStatus;
        System.out.println("\n=== Cambio de estado de orden " + orderId + " ===");
        System.out.println("Nuevo estado: " + newStatus);
        notifyObservers(message);
    }

    private void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(orderId, currentStatus, message);
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }
}
