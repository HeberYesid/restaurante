package com.example.patterns.creational.abstractfactory;

/**
 * Patrón Abstract Factory
 * Productos abstractos para ingredientes
 */
public interface Ingredient {
    String getName();
    double getCost();
    String getOrigin();
}
