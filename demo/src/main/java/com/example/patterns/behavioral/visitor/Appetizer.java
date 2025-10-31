package com.example.patterns.behavioral.visitor;

public class Appetizer implements VisitableMenuItem {
    private String name;
    private double price;
    private boolean isShared;

    public Appetizer(String name, double price, boolean isShared) {
        this.name = name;
        this.price = price;
        this.isShared = isShared;
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

    public boolean isShared() {
        return isShared;
    }
}
