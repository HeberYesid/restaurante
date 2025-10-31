package com.example.patterns.structural.decorator;

public class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Leche";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.75;
    }
}
