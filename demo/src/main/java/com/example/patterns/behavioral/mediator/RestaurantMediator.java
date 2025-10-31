package com.example.patterns.behavioral.mediator;

/**
 * Patrón Mediator
 * Mediador para comunicación entre componentes del restaurante
 */
public interface RestaurantMediator {
    void notify(Component sender, String event);
    void registerComponent(Component component);
}
