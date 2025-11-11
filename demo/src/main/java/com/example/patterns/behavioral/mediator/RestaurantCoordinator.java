package com.example.patterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediador concreto
 */
public class RestaurantCoordinator implements RestaurantMediator {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantCoordinator.class);
    private List<Component> components = new ArrayList<>();

    @Override
    public void registerComponent(Component component) {
        components.add(component);
        logger.info("Componente registrado: {}", component.getName());
    }

    @Override
    public void notify(Component sender, String event) {
        logger.info("\n[MEDIATOR] Evento de {}: {}", sender.getName(), event);
        
        if (event.startsWith("ORDER_RECEIVED:")) {
            // Notificar a cocina
            components.stream()
                    .filter(c -> c instanceof Kitchen)
                    .forEach(c -> c.receive(event));
        } else if (event.startsWith("ORDER_READY:")) {
            // Notificar a delivery
            components.stream()
                    .filter(c -> c instanceof DeliveryService)
                    .forEach(c -> c.receive(event));
        } else if (event.startsWith("ORDER_DELIVERED:")) {
            // Notificar a recepción
            components.stream()
                    .filter(c -> c instanceof FrontDesk)
                    .forEach(c -> c.receive("Confirmación: " + event));
        }
        
        logger.info("");
    }
}
