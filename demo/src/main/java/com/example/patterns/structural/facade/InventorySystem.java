package com.example.patterns.structural.facade;

/**
 * Subsistemas complejos
 */
public class InventorySystem {
    public boolean checkAvailability(String item) {
        System.out.println("Verificando disponibilidad de: " + item);
        return true;
    }

    public void updateStock(String item, int quantity) {
        System.out.println("Actualizando inventario: " + item + " (-" + quantity + ")");
    }
}
