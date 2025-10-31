package com.example.patterns.behavioral.visitor;

public class Drink implements VisitableMenuItem {
    private String name;
    private double price;
    private int volumeMl;

    public Drink(String name, double price, int volumeMl) {
        this.name = name;
        this.price = price;
        this.volumeMl = volumeMl;
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

    public int getVolumeMl() {
        return volumeMl;
    }
}
