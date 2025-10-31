package com.example.patterns.creational.abstractfactory;

public class BudgetIngredientFactory implements IngredientFactory {
    @Override
    public Meat createMeat() {
        return new RegularBeef();
    }

    @Override
    public Vegetable createVegetable() {
        return new RegularTomato();
    }

    @Override
    public Cheese createCheese() {
        return new CheddarCheese();
    }
}
