package com.example.patterns.creational.abstractfactory;

public class OrganicTomato implements Vegetable {
    @Override
    public String getName() {
        return "Tomate Orgánico";
    }

    @Override
    public double getCost() {
        return 4.50;
    }

    @Override
    public String getOrigin() {
        return "Huerta Local";
    }

    @Override
    public boolean isOrganic() {
        return true;
    }
}
