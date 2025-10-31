package com.example.patterns.creational.factorymethod;

public class Pasta extends Dish {
    public Pasta() {
        this.name = "Pasta Carbonara";
        this.price = 12.99;
        this.description = "Pasta con salsa carbonara, panceta y parmesano";
    }

    @Override
    public void prepare() {
        System.out.println("Preparando ingredientes para la pasta...");
    }

    @Override
    public void cook() {
        System.out.println("Hirviendo pasta al dente...");
    }

    @Override
    public void serve() {
        System.out.println("Sirviendo pasta con queso parmesano rallado.");
    }
}
