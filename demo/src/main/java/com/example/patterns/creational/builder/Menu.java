package com.example.patterns.creational.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Patrón Builder
 * Representa un menú personalizado en el restaurante
 */
public class Menu {
    private final String name;
    private final List<String> appetizers;
    private final List<String> mainCourses;
    private final List<String> desserts;
    private final List<String> beverages;
    private final boolean isVegetarian;
    private final boolean isGlutenFree;
    private final double totalPrice;
    private final String specialInstructions;

    private Menu(Builder builder) {
        this.name = builder.name;
        this.appetizers = builder.appetizers;
        this.mainCourses = builder.mainCourses;
        this.desserts = builder.desserts;
        this.beverages = builder.beverages;
        this.isVegetarian = builder.isVegetarian;
        this.isGlutenFree = builder.isGlutenFree;
        this.totalPrice = builder.totalPrice;
        this.specialInstructions = builder.specialInstructions;
    }

    public String getName() {
        return name;
    }

    public List<String> getAppetizers() {
        return new ArrayList<>(appetizers);
    }

    public List<String> getMainCourses() {
        return new ArrayList<>(mainCourses);
    }

    public List<String> getDesserts() {
        return new ArrayList<>(desserts);
    }

    public List<String> getBeverages() {
        return new ArrayList<>(beverages);
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== MENÚ: ").append(name).append(" =====\n");
        
        if (!appetizers.isEmpty()) {
            sb.append("\nEntradas:\n");
            appetizers.forEach(a -> sb.append("  - ").append(a).append("\n"));
        }
        
        if (!mainCourses.isEmpty()) {
            sb.append("\nPlatos principales:\n");
            mainCourses.forEach(m -> sb.append("  - ").append(m).append("\n"));
        }
        
        if (!desserts.isEmpty()) {
            sb.append("\nPostres:\n");
            desserts.forEach(d -> sb.append("  - ").append(d).append("\n"));
        }
        
        if (!beverages.isEmpty()) {
            sb.append("\nBebidas:\n");
            beverages.forEach(b -> sb.append("  - ").append(b).append("\n"));
        }
        
        sb.append("\nCaracterísticas:\n");
        sb.append("  Vegetariano: ").append(isVegetarian ? "Sí" : "No").append("\n");
        sb.append("  Sin gluten: ").append(isGlutenFree ? "Sí" : "No").append("\n");
        
        if (specialInstructions != null && !specialInstructions.isEmpty()) {
            sb.append("\nInstrucciones especiales: ").append(specialInstructions).append("\n");
        }
        
        sb.append("\nPrecio total: $").append(String.format("%.2f", totalPrice));
        
        return sb.toString();
    }

    public static class Builder {
        private String name = "Menú Personalizado";
        private List<String> appetizers = new ArrayList<>();
        private List<String> mainCourses = new ArrayList<>();
        private List<String> desserts = new ArrayList<>();
        private List<String> beverages = new ArrayList<>();
        private boolean isVegetarian = false;
        private boolean isGlutenFree = false;
        private double totalPrice = 0.0;
        private String specialInstructions = "";

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder addAppetizer(String appetizer, double price) {
            this.appetizers.add(appetizer);
            this.totalPrice += price;
            return this;
        }

        public Builder addMainCourse(String mainCourse, double price) {
            this.mainCourses.add(mainCourse);
            this.totalPrice += price;
            return this;
        }

        public Builder addDessert(String dessert, double price) {
            this.desserts.add(dessert);
            this.totalPrice += price;
            return this;
        }

        public Builder addBeverage(String beverage, double price) {
            this.beverages.add(beverage);
            this.totalPrice += price;
            return this;
        }

        public Builder vegetarian(boolean isVegetarian) {
            this.isVegetarian = isVegetarian;
            return this;
        }

        public Builder glutenFree(boolean isGlutenFree) {
            this.isGlutenFree = isGlutenFree;
            return this;
        }

        public Builder specialInstructions(String instructions) {
            this.specialInstructions = instructions;
            return this;
        }

        public Menu build() {
            return new Menu(this);
        }
    }
}
