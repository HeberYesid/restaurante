package com.example.patterns.creational.factorymethod;

public class Pizza extends Dish {
    public Pizza() {
        this.name = "Pizza Margherita";
        this.price = 15.99;
        this.description = "Pizza italiana con tomate, mozzarella y albahaca";
    }

    @Override
    public void prepare() {
        System.out.println("Preparando masa de pizza...");
    }

    @Override
    public void cook() {
        System.out.println("Cocinando pizza en horno de leña a 400°C...");
    }

    @Override
    public void serve() {
        System.out.println("Sirviendo pizza caliente con aceite de oliva.");
    }
}
