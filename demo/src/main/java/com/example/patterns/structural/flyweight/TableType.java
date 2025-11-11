package com.example.patterns.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Patrón Flyweight
 * Estado intrínseco compartido (inmutable)
 */
public class TableType {
    private static final Logger logger = LoggerFactory.getLogger(TableType.class);
    private final String type;
    private final int capacity;
    private final String location;
    private final boolean hasWindowView;

    public TableType(String type, int capacity, String location, boolean hasWindowView) {
        this.type = type;
        this.capacity = capacity;
        this.location = location;
        this.hasWindowView = hasWindowView;
        
        // Simular carga pesada de recursos
        logger.info("Creando nuevo tipo de mesa (recurso pesado): {}", type);
    }

    public void displayInfo(int tableNumber, boolean isOccupied) {
        logger.info("Mesa #{} - Tipo: {}, Capacidad: {}, Ubicación: {}, Vista: {}, Estado: {}",
                         tableNumber, type, capacity, location,
                         hasWindowView ? "Sí" : "No",
                         isOccupied ? "Ocupada" : "Libre");
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public boolean hasWindowView() {
        return hasWindowView;
    }
}
