package com.example.patterns.creational.factorymethod;

/**
 * Patrón Factory Method - Producto abstracto
 * Representa un plato en el restaurante
 */
public abstract class Dish {
    protected String name;
    protected double price;
    protected String description;

    public abstract void prepare();
    public abstract void cook();
    public abstract void serve();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f: %s", name, price, description);
    }
}
