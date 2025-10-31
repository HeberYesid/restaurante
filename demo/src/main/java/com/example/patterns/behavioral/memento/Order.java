package com.example.patterns.behavioral.memento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Memento - Originator
 * Representa el estado de una orden
 */
public class Order {
    private String orderId;
    private List<String> items;
    private double total;
    private String status;
    private LocalDateTime timestamp;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.status = "NUEVA";
        this.timestamp = LocalDateTime.now();
    }

    public void addItem(String item, double price) {
        items.add(item);
        total += price;
        timestamp = LocalDateTime.now();
    }

    public void removeItem(String item, double price) {
        items.remove(item);
        total -= price;
        timestamp = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
        timestamp = LocalDateTime.now();
    }

    // Crear Memento
    public OrderMemento save() {
        return new OrderMemento(
            new ArrayList<>(items),
            total,
            status,
            timestamp
        );
    }

    // Restaurar desde Memento
    public void restore(OrderMemento memento) {
        this.items = new ArrayList<>(memento.getItems());
        this.total = memento.getTotal();
        this.status = memento.getStatus();
        this.timestamp = memento.getTimestamp();
    }

    @Override
    public String toString() {
        return String.format("Orden %s [%s] - %d items, Total: $%.2f, Timestamp: %s",
                           orderId, status, items.size(), total, timestamp);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
