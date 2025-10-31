package com.example.patterns.structural.composite;

/**
 * Hoja - Elemento individual del menú
 */
public class MenuItem extends MenuComponent {
    private double price;
    private boolean vegetarian;

    public MenuItem(String name, String description, double price, boolean vegetarian) {
        super(name, description);
        this.price = price;
        this.vegetarian = vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print(String indent) {
        System.out.printf("%s%s - $%.2f%s\n", 
                         indent, name, price, 
                         vegetarian ? " (V)" : "");
        System.out.printf("%s  %s\n", indent, description);
    }
}
