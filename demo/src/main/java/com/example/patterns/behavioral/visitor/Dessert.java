package com.example.patterns.behavioral.visitor;

public class Dessert implements VisitableMenuItem {
    private String name;
    private double price;
    private int sugarGrams;

    public Dessert(String name, double price, int sugarGrams) {
        this.name = name;
        this.price = price;
        this.sugarGrams = sugarGrams;
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

    public int getSugarGrams() {
        return sugarGrams;
    }
}
