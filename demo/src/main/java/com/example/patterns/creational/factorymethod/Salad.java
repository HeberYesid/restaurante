package com.example.patterns.creational.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Salad extends Dish {
    private static final Logger logger = LoggerFactory.getLogger(Salad.class);
    
    public Salad() {
        this.name = "Ensalada César";
        this.price = 8.99;
        this.description = "Lechuga romana, pollo, crutones y aderezo césar";
    }

    @Override
    public void prepare() {
        logger.info("Lavando y cortando vegetales frescos...");
    }

    @Override
    public void cook() {
        logger.info("Asando pollo para la ensalada...");
    }

    @Override
    public void serve() {
        logger.info("Sirviendo ensalada fresca con aderezo.");
    }
}
