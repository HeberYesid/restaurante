package com.example.patterns.behavioral.visitor;

/**
 * Elemento que puede ser visitado
 */
public interface VisitableMenuItem {
    void accept(MenuItemVisitor visitor);
    String getName();
    double getPrice();
}
