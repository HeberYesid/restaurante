package com.example.patterns.creational.abstractfactory;

// Implementaciones concretas - Ingredientes Premium
public class WagyuBeef implements Meat {
    @Override
    public String getName() {
        return "Wagyu Beef";
    }

    @Override
    public double getCost() {
        return 85.00;
    }

    @Override
    public String getOrigin() {
        return "Japón";
    }

    @Override
    public String getCut() {
        return "Ribeye";
    }
}
