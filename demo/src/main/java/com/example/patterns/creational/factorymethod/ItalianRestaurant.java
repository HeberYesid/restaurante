package com.example.patterns.creational.factorymethod;

public class ItalianRestaurant extends Restaurant {
    @Override
    public Dish createDish() {
        return new Pizza();
    }
}
