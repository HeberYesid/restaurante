package com.example.patterns.behavioral.visitor;

public class MainCourse implements VisitableMenuItem {
    private String name;
    private double price;
    private int calories;

    public MainCourse(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    @Override
    public void accept(MenuItemVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }
}
