package com.example.patterns.behavioral.strategy;

/**
 * Patrón Strategy - Estrategia de precios
 */
public interface PricingStrategy {
    double calculatePrice(double basePrice);
    String getStrategyName();
}
