package com.example.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Compuesto - Categoría o sección del menú que contiene otros componentes
 */
public class MenuCategory extends MenuComponent {
    private List<MenuComponent> children = new ArrayList<>();

    public MenuCategory(String name, String description) {
        super(name, description);
    }

    @Override
    public void add(MenuComponent component) {
        children.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        children.remove(component);
    }

    @Override
    public MenuComponent getChild(int index) {
        return children.get(index);
    }

    @Override
    public double getPrice() {
        // El precio de una categoría es la suma de todos sus elementos
        return children.stream()
                      .mapToDouble(MenuComponent::getPrice)
                      .sum();
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "=== " + name + " ===");
        System.out.println(indent + description);
        System.out.println();
        
        for (MenuComponent component : children) {
            component.print(indent + "  ");
        }
    }

    public int getItemCount() {
        return children.size();
    }

    public List<MenuComponent> getChildren() {
        return new ArrayList<>(children);
    }
}
