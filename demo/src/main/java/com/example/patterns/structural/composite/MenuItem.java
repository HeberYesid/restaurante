package com.example.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hoja - Elemento individual del menú
 */
public class MenuItem extends MenuComponent {
    private static final Logger logger = LoggerFactory.getLogger(MenuItem.class);
    private double price;
    private boolean vegetarian;

    public MenuItem(String name, String description, double price, boolean vegetarian) {
        super(name, description);
        this.price = price;
        this.vegetarian = vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print(String indent) {
        logger.info("{}{} - ${}{}", 
                         indent, name, String.format("%.2f", price), 
                         vegetarian ? " (V)" : "");
        logger.info("{}  {}", indent, description);
    }
}
