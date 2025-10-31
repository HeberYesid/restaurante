package com.example.patterns.behavioral.strategy;

public class WeekendPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 1.15; // 15% recargo
    }

    @Override
    public String getStrategyName() {
        return "Fin de Semana (15% recargo)";
    }
}
