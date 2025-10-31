package com.example.patterns.creational.factorymethod;

public class Salad extends Dish {
    public Salad() {
        this.name = "Ensalada César";
        this.price = 8.99;
        this.description = "Lechuga romana, pollo, crutones y aderezo césar";
    }

    @Override
    public void prepare() {
        System.out.println("Lavando y cortando vegetales frescos...");
    }

    @Override
    public void cook() {
        System.out.println("Asando pollo para la ensalada...");
    }

    @Override
    public void serve() {
        System.out.println("Sirviendo ensalada fresca con aderezo.");
    }
}
