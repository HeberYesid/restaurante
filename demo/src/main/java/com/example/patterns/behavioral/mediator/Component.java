package com.example.patterns.behavioral.mediator;

/**
 * Componente base
 */
public abstract class Component {
    protected RestaurantMediator mediator;
    protected String name;

    public Component(RestaurantMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void receive(String message);
}
