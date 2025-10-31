package com.example.patterns.structural.decorator;

public class Coffee implements Beverage {
    @Override
    public String getDescription() {
        return "Café";
    }

    @Override
    public double getCost() {
        return 3.50;
    }
}
