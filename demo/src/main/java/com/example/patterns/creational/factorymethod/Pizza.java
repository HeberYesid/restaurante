package com.example.patterns.creational.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pizza extends Dish {
    private static final Logger logger = LoggerFactory.getLogger(Pizza.class);
    
    public Pizza() {
        this.name = "Pizza Margherita";
        this.price = 15.99;
        this.description = "Pizza italiana con tomate, mozzarella y albahaca";
    }

    @Override
    public void prepare() {
        logger.info("Preparando masa de pizza...");
    }

    @Override
    public void cook() {
        logger.info("Cocinando pizza en horno de leña a 400°C...");
    }

    @Override
    public void serve() {
        logger.info("Sirviendo pizza caliente con aceite de oliva.");
    }
}
