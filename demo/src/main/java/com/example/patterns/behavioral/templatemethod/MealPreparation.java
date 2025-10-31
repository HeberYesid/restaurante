package com.example.patterns.behavioral.templatemethod;

/**
 * Patrón Template Method
 * Define el esqueleto de preparación de una comida
 */
public abstract class MealPreparation {
    
    // Template Method
    public final void prepareMeal() {
        System.out.println("\n=== Iniciando preparación de comida ===");
        gatherIngredients();
        prepareIngredients();
        cook();
        
        if (customerWantsCondiments()) {
            addCondiments();
        }
        
        serve();
        System.out.println("=== Comida lista ===\n");
    }

    // Pasos abstractos que deben implementar las subclases
    protected abstract void gatherIngredients();
    protected abstract void prepareIngredients();
    protected abstract void cook();
    protected abstract void serve();

    // Hook method con implementación por defecto
    protected void addCondiments() {
        System.out.println("Agregando condimentos estándar");
    }

    // Hook method que las subclases pueden sobrescribir
    protected boolean customerWantsCondiments() {
        return true;
    }
}
