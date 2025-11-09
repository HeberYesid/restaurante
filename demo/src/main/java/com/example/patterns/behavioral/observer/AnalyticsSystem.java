package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnalyticsSystem implements OrderObserver {
    private static final Logger logger = LoggerFactory.getLogger(AnalyticsSystem.class);
    private int ordersTracked = 0;

    @Override
    public void update(String orderId, String status, String message) {
        ordersTracked++;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        logger.info("[SISTEMA DE ANALÍTICAS]");
        logger.info("  [{}] Log: Orden {} cambió a {}", timestamp, orderId, status);
        logger.info("  Total órdenes rastreadas: {}", ordersTracked);
    }

    @Override
    public String getObserverName() {
        return "Sistema de Analíticas";
    }

    public int getOrdersTracked() {
        return ordersTracked;
    }
}
