package com.example.patterns.behavioral.strategy;

/**
 * Context que usa la estrategia
 */
public class MenuItem {
    private String name;
    private double basePrice;
    private PricingStrategy pricingStrategy;

    public MenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.pricingStrategy = new RegularPricing();
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public double getPrice() {
        return pricingStrategy.calculatePrice(basePrice);
    }

    public String getPriceInfo() {
        return String.format("%s - Precio base: $%.2f, %s: $%.2f",
                           name, basePrice, 
                           pricingStrategy.getStrategyName(), 
                           getPrice());
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
