package com.example.patterns.behavioral.visitor;

/**
 * Visitor concreto para análisis nutricional
 */
public class NutritionalAnalysisVisitor implements MenuItemVisitor {
    private int totalCalories = 0;
    private int totalSugar = 0;

    @Override
    public void visit(Appetizer appetizer) {
        System.out.println("  Analizando entrada: " + appetizer.getName());
    }

    @Override
    public void visit(MainCourse mainCourse) {
        totalCalories += mainCourse.getCalories();
        System.out.println("  Plato principal: " + mainCourse.getName() + 
                         " - " + mainCourse.getCalories() + " calorías");
    }

    @Override
    public void visit(Dessert dessert) {
        totalSugar += dessert.getSugarGrams();
        System.out.println("  Postre: " + dessert.getName() + 
                         " - " + dessert.getSugarGrams() + "g azúcar");
    }

    @Override
    public void visit(Drink drink) {
        System.out.println("  Bebida: " + drink.getName() + 
                         " - " + drink.getVolumeMl() + "ml");
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getTotalSugar() {
        return totalSugar;
    }

    public void reset() {
        totalCalories = 0;
        totalSugar = 0;
    }
}
