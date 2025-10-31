package com.example.patterns.creational.abstractfactory;

/**
 * Abstract Factory - Fábrica abstracta de ingredientes
 */
public interface IngredientFactory {
    Meat createMeat();
    Vegetable createVegetable();
    Cheese createCheese();
}
