package com.example.patterns.structural.decorator;

public class Juice implements Beverage {
    @Override
    public String getDescription() {
        return "Jugo natural";
    }

    @Override
    public double getCost() {
        return 4.00;
    }
}
