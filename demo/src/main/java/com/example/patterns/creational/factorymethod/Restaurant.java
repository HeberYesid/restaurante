package com.example.patterns.creational.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Patrón Factory Method - Creador abstracto
 * Define el método factory que las subclases implementarán
 */
public abstract class Restaurant {
    private static final Logger logger = LoggerFactory.getLogger(Restaurant.class);
    
    // Factory Method
    public abstract Dish createDish();
    
    // Template Method que usa el Factory Method
    public void orderDish() {
        Dish dish = createDish();
        logger.info("\n=== Preparando orden: {} ===", dish.getName());
        dish.prepare();
        dish.cook();
        dish.serve();
        logger.info("Orden completada. Total: ${}", dish.getPrice());
    }
}
