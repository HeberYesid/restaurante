package com.example.patterns.creational.abstractfactory;

// Implementaciones concretas - Ingredientes Económicos
public class RegularBeef implements Meat {
    @Override
    public String getName() {
        return "Carne de Res Regular";
    }

    @Override
    public double getCost() {
        return 12.00;
    }

    @Override
    public String getOrigin() {
        return "Local";
    }

    @Override
    public String getCut() {
        return "Sirloin";
    }
}
