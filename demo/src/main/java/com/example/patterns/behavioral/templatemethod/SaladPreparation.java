package com.example.patterns.behavioral.templatemethod;

public class SaladPreparation extends MealPreparation {
    @Override
    protected void gatherIngredients() {
        System.out.println("Reuniendo: lechuga, tomate, pepino, cebolla, aceitunas");
    }

    @Override
    protected void prepareIngredients() {
        System.out.println("Lavando y cortando todos los vegetales");
    }

    @Override
    protected void cook() {
        System.out.println("No requiere cocción (ensalada fresca)");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Agregando vinagreta y aceite de oliva");
    }

    @Override
    protected void serve() {
        System.out.println("Sirviendo ensalada en bowl grande");
    }
}
