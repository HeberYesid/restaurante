package com.example.patterns.behavioral.strategy;

public class RegularPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }

    @Override
    public String getStrategyName() {
        return "Precio Regular";
    }
}
