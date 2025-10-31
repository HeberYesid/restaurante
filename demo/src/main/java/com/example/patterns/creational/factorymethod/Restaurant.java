package com.example.patterns.creational.factorymethod;

/**
 * Patrón Factory Method - Creador abstracto
 * Define el método factory que las subclases implementarán
 */
public abstract class Restaurant {
    
    // Factory Method
    public abstract Dish createDish();
    
    // Template Method que usa el Factory Method
    public void orderDish() {
        Dish dish = createDish();
        System.out.println("\n=== Preparando orden: " + dish.getName() + " ===");
        dish.prepare();
        dish.cook();
        dish.serve();
        System.out.println("Orden completada. Total: $" + dish.getPrice());
    }
}
