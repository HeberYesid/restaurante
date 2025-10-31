package com.example.patterns.creational.factorymethod;

public class PastaRestaurant extends Restaurant {
    @Override
    public Dish createDish() {
        return new Pasta();
    }
}
