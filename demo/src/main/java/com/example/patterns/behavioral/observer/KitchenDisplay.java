package com.example.patterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KitchenDisplay implements OrderObserver {
    private static final Logger logger = LoggerFactory.getLogger(KitchenDisplay.class);
    private int displayNumber;

    public KitchenDisplay(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    @Override
    public void update(String orderId, String status, String message) {
        logger.info("[PANTALLA COCINA #{}]", displayNumber);
        logger.info("  >>> Orden {}: {}", orderId, status);
        if (status.equals("CONFIRMADA")) {
            logger.info("  >>> Iniciar preparación");
        }
    }

    @Override
    public String getObserverName() {
        return "Pantalla Cocina #" + displayNumber;
    }
}
