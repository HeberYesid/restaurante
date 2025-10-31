package com.example.patterns.structural.decorator;

public class SugarDecorator extends BeverageDecorator {
    public SugarDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Azúcar";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.25;
    }
}
