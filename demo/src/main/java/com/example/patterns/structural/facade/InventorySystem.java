package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subsistemas complejos
 */
public class InventorySystem {
    private static final Logger logger = LoggerFactory.getLogger(InventorySystem.class);
    
    public boolean checkAvailability(String item) {
        logger.info("Verificando disponibilidad de: {}", item);
        return true;
    }

    public void updateStock(String item, int quantity) {
        logger.info("Actualizando inventario: {} (-{})", item, quantity);
    }
}
