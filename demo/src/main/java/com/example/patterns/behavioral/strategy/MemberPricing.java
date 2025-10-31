package com.example.patterns.behavioral.strategy;

public class MemberPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.85; // 15% descuento para miembros
    }

    @Override
    public String getStrategyName() {
        return "Precio de Miembro (15% descuento)";
    }
}
