package com.example.patterns.structural.flyweight;

/**
 * Patrón Flyweight
 * Estado intrínseco compartido (inmutable)
 */
public class TableType {
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
        System.out.println("Creando nuevo tipo de mesa (recurso pesado): " + type);
    }

    public void displayInfo(int tableNumber, boolean isOccupied) {
        System.out.printf("Mesa #%d - Tipo: %s, Capacidad: %d, Ubicación: %s, Vista: %s, Estado: %s\n",
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
