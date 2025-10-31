package com.example.patterns.behavioral.templatemethod;

public class BurgerPreparation extends MealPreparation {
    @Override
    protected void gatherIngredients() {
        System.out.println("Reuniendo: pan, carne, lechuga, tomate, queso");
    }

    @Override
    protected void prepareIngredients() {
        System.out.println("Cortando vegetales, formando hamburguesa");
    }

    @Override
    protected void cook() {
        System.out.println("Asando hamburguesa a la parrilla por 5 minutos cada lado");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Agregando ketchup, mostaza y mayonesa");
    }

    @Override
    protected void serve() {
        System.out.println("Sirviendo hamburguesa con papas fritas");
    }
}
