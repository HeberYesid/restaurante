package com.example.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Receiver - Orden de cocina
 */
public class KitchenOrder {
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
        System.out.println("Agregado a la orden: " + item);
    }

    public void removeItem(String item) {
        if (items.remove(item)) {
            System.out.println("Removido de la orden: " + item);
        }
    }

    public void prepareOrder() {
        System.out.println("Preparando orden con " + items.size() + " items:");
        items.forEach(item -> System.out.println("  - " + item));
    }

    public void cancelOrder() {
        System.out.println("Cancelando orden completa");
        items.clear();
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
