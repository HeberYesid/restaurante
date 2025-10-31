package com.example.patterns.behavioral.visitor;

/**
 * Patrón Visitor - Visitor
 */
public interface MenuItemVisitor {
    void visit(Appetizer appetizer);
    void visit(MainCourse mainCourse);
    void visit(Dessert dessert);
    void visit(Drink drink);
}
