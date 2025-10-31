package com.example.patterns.creational.abstractfactory;

public class ParmesanCheese implements Cheese {
    @Override
    public String getName() {
        return "Parmigiano Reggiano";
    }

    @Override
    public double getCost() {
        return 22.00;
    }

    @Override
    public String getOrigin() {
        return "Italia";
    }

    @Override
    public int getAgingMonths() {
        return 24;
    }
}
