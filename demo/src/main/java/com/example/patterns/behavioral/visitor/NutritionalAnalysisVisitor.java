package com.example.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visitor concreto para análisis nutricional
 */
public class NutritionalAnalysisVisitor implements MenuItemVisitor {
    private static final Logger logger = LoggerFactory.getLogger(NutritionalAnalysisVisitor.class);
    private int totalCalories = 0;
    private int totalSugar = 0;

    @Override
    public void visit(Appetizer appetizer) {
        logger.info("  Analizando entrada: {}", appetizer.getName());
    }

    @Override
    public void visit(MainCourse mainCourse) {
        totalCalories += mainCourse.getCalories();
        logger.info("  Plato principal: {} - {} calorías", 
                         mainCourse.getName(), mainCourse.getCalories());
    }

    @Override
    public void visit(Dessert dessert) {
        totalSugar += dessert.getSugarGrams();
        logger.info("  Postre: {} - {}g azúcar", 
                         dessert.getName(), dessert.getSugarGrams());
    }

    @Override
    public void visit(Drink drink) {
        logger.info("  Bebida: {} - {}ml", 
                         drink.getName(), drink.getVolumeMl());
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
