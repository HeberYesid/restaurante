package com.example.patterns.structural.composite;

/**
 * Patrón Composite - Componente
 * Representa elementos del menú que pueden tener precio
 */
public abstract class MenuComponent {
    protected String name;
    protected String description;

    public MenuComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Operaciones para compuestos
    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    // Operaciones comunes
    public abstract double getPrice();
    public abstract void print(String indent);
}
