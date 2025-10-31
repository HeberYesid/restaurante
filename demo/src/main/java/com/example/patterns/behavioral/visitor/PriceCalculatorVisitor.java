package com.example.patterns.behavioral.visitor;

/**
 * Visitor concreto para calcular precio total
 */
public class PriceCalculatorVisitor implements MenuItemVisitor {
    private double totalPrice = 0.0;

    @Override
    public void visit(Appetizer appetizer) {
        double price = appetizer.getPrice();
        if (appetizer.isShared()) {
            price *= 0.9; // 10% descuento si es para compartir
        }
        totalPrice += price;
        System.out.println("  Entrada: " + appetizer.getName() + " - $" + price);
    }

    @Override
    public void visit(MainCourse mainCourse) {
        totalPrice += mainCourse.getPrice();
        System.out.println("  Plato principal: " + mainCourse.getName() + 
                         " - $" + mainCourse.getPrice());
    }

    @Override
    public void visit(Dessert dessert) {
        totalPrice += dessert.getPrice();
        System.out.println("  Postre: " + dessert.getName() + " - $" + dessert.getPrice());
    }

    @Override
    public void visit(Drink drink) {
        totalPrice += drink.getPrice();
        System.out.println("  Bebida: " + drink.getName() + " - $" + drink.getPrice());
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void reset() {
        totalPrice = 0.0;
    }
}
