package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Patrón Template Method
 * Define el esqueleto de preparación de una comida
 */
public abstract class MealPreparation {
    private static final Logger logger = LoggerFactory.getLogger(MealPreparation.class);
    
    // Template Method
    public final void prepareMeal() {
        logger.info("\n=== Iniciando preparación de comida ===");
        gatherIngredients();
        prepareIngredients();
        cook();
        
        if (customerWantsCondiments()) {
            addCondiments();
        }
        
        serve();
        logger.info("=== Comida lista ===\n");
    }

    // Pasos abstractos que deben implementar las subclases
    protected abstract void gatherIngredients();
    protected abstract void prepareIngredients();
    protected abstract void cook();
    protected abstract void serve();

    // Hook method con implementación por defecto
    protected void addCondiments() {
        logger.info("Agregando condimentos estándar");
    }

    // Hook method que las subclases pueden sobrescribir
    protected boolean customerWantsCondiments() {
        return true;
    }
}
