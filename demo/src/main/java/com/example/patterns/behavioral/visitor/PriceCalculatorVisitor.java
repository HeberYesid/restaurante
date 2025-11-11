package com.example.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor concreto para calcular precio total
 */
public class PriceCalculatorVisitor implements MenuItemVisitor {
    private static final Logger logger = LoggerFactory.getLogger(PriceCalculatorVisitor.class);
    private double totalPrice = 0.0;

    @Override
    public void visit(Appetizer appetizer) {
        double price = appetizer.getPrice();
        if (appetizer.isShared()) {
            price *= 0.9; // 10% descuento si es para compartir
        }
        totalPrice += price;
        logger.info("  Entrada: {} - ${}", appetizer.getName(), price);
    }

    @Override
    public void visit(MainCourse mainCourse) {
        totalPrice += mainCourse.getPrice();
        logger.info("  Plato principal: {} - ${}", mainCourse.getName(), mainCourse.getPrice());
    }

    @Override
    public void visit(Dessert dessert) {
        totalPrice += dessert.getPrice();
        logger.info("  Postre: {} - ${}", dessert.getName(), dessert.getPrice());
    }

    @Override
    public void visit(Drink drink) {
        totalPrice += drink.getPrice();
        logger.info("  Bebida: {} - ${}", drink.getName(), drink.getPrice());
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void reset() {
        totalPrice = 0.0;
    }
}
