package com.example.patterns.creational.abstractfactory;

public class CheddarCheese implements Cheese {
    @Override
    public String getName() {
        return "Queso Cheddar";
    }

    @Override
    public double getCost() {
        return 6.00;
    }

    @Override
    public String getOrigin() {
        return "Local";
    }

    @Override
    public int getAgingMonths() {
        return 3;
    }
}
