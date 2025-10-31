package com.example.patterns.structural.decorator;

public class CaramelDecorator extends BeverageDecorator {
    public CaramelDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Caramelo";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.80;
    }
}
