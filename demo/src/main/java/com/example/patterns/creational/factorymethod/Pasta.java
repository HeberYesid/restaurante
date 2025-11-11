package com.example.patterns.creational.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pasta extends Dish {
    private static final Logger logger = LoggerFactory.getLogger(Pasta.class);
    
    public Pasta() {
        this.name = "Pasta Carbonara";
        this.price = 12.99;
        this.description = "Pasta con salsa carbonara, panceta y parmesano";
    }

    @Override
    public void prepare() {
        logger.info("Preparando ingredientes para la pasta...");
    }

    @Override
    public void cook() {
        logger.info("Hirviendo pasta al dente...");
    }

    @Override
    public void serve() {
        logger.info("Sirviendo pasta con queso parmesano rallado.");
    }
}
