package com.example.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Compuesto - Categoría o sección del menú que contiene otros componentes
 */
public class MenuCategory extends MenuComponent {
    private static final Logger logger = LoggerFactory.getLogger(MenuCategory.class);
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
        logger.info("{}{} {}", indent, "===", name + " ===");
        logger.info("{}{}", indent, description);
        logger.info("");
        
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
