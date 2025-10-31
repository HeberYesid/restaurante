package com.example.patterns.behavioral.observer;

/**
 * Patrón Observer - Observer
 */
public interface OrderObserver {
    void update(String orderId, String status, String message);
    String getObserverName();
}
