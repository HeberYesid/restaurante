package com.example.patterns.creational.abstractfactory;

public class PremiumIngredientFactory implements IngredientFactory {
    @Override
    public Meat createMeat() {
        return new WagyuBeef();
    }

    @Override
    public Vegetable createVegetable() {
        return new OrganicTomato();
    }

    @Override
    public Cheese createCheese() {
        return new ParmesanCheese();
    }
}
