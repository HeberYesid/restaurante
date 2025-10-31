package com.example.patterns.structural.decorator;

public class Tea implements Beverage {
    @Override
    public String getDescription() {
        return "Té";
    }

    @Override
    public double getCost() {
        return 2.50;
    }
}
