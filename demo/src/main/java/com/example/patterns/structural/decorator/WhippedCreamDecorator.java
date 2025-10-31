package com.example.patterns.structural.decorator;

public class WhippedCreamDecorator extends BeverageDecorator {
    public WhippedCreamDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Crema batida";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 1.00;
    }
}
