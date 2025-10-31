package com.example.patterns.behavioral.strategy;

public class HappyHourPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.7; // 30% descuento
    }

    @Override
    public String getStrategyName() {
        return "Happy Hour (30% descuento)";
    }
}
