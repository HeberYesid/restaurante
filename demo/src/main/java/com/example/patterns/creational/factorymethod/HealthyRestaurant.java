package com.example.patterns.creational.factorymethod;

public class HealthyRestaurant extends Restaurant {
    @Override
    public Dish createDish() {
        return new Salad();
    }
}
