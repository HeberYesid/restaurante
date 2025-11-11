package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Receiver - Orden de cocina
 */
public class KitchenOrder {
    private static final Logger logger = LoggerFactory.getLogger(KitchenOrder.class);
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
        logger.info("Agregado a la orden: {}", item);
    }

    public void removeItem(String item) {
        if (items.remove(item)) {
            logger.info("Removido de la orden: {}", item);
        }
    }

    public void prepareOrder() {
        logger.info("Preparando orden con {} items:", items.size());
        items.forEach(item -> logger.info("  - {}", item));
    }

    public void cancelOrder() {
        logger.info("Cancelando orden completa");
        items.clear();
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
