package com.example.patterns.behavioral.memento;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Memento - Captura el estado de la orden
 */
public class OrderMemento {
    private final List<String> items;
    private final double total;
    private final String status;
    private final LocalDateTime timestamp;

    public OrderMemento(List<String> items, double total, String status, LocalDateTime timestamp) {
        this.items = items;
        this.total = total;
        this.status = status;
        this.timestamp = timestamp;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
