package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject - Observable
 */
public class OrderTracker {
    private static final Logger logger = LoggerFactory.getLogger(OrderTracker.class);
    private List<OrderObserver> observers = new ArrayList<>();
    private String orderId;
    private String currentStatus;

    public OrderTracker(String orderId) {
        this.orderId = orderId;
        this.currentStatus = "CREADA";
    }

    public void attach(OrderObserver observer) {
        observers.add(observer);
        logger.debug("Observer registrado: {}", observer.getObserverName());
    }

    public void detach(OrderObserver observer) {
        observers.remove(observer);
        logger.debug("Observer removido: {}", observer.getObserverName());
    }

    public void updateStatus(String newStatus, String message) {
        this.currentStatus = newStatus;
        logger.info("\n=== Cambio de estado de orden {} ===", orderId);
        logger.info("Nuevo estado: {}", newStatus);
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
