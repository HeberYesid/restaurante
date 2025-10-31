package com.example.patterns.behavioral.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnalyticsSystem implements OrderObserver {
    private int ordersTracked = 0;

    @Override
    public void update(String orderId, String status, String message) {
        ordersTracked++;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[SISTEMA DE ANALÍTICAS]");
        System.out.println("  [" + timestamp + "] Log: Orden " + orderId + 
                         " cambió a " + status);
        System.out.println("  Total órdenes rastreadas: " + ordersTracked);
    }

    @Override
    public String getObserverName() {
        return "Sistema de Analíticas";
    }

    public int getOrdersTracked() {
        return ordersTracked;
    }
}
