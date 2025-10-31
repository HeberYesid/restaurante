package com.example.patterns.creational.abstractfactory;

public class RegularTomato implements Vegetable {
    @Override
    public String getName() {
        return "Tomate Regular";
    }

    @Override
    public double getCost() {
        return 1.50;
    }

    @Override
    public String getOrigin() {
        return "Supermercado";
    }

    @Override
    public boolean isOrganic() {
        return false;
    }
}
