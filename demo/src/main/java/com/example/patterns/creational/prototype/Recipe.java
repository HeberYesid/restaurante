package com.example.patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Prototype
 * Representa una receta que puede ser clonada usando copy constructor
 * en lugar de Cloneable (mejor práctica según Joshua Bloch - Effective Java)
 */
public class Recipe {
    private String name;
    private String category;
    private int preparationTime; // minutos
    private int cookingTime; // minutos
    private int servings;
    private Map<String, String> ingredients;
    private String instructions;

    public Recipe(String name, String category) {
        this.name = name;
        this.category = category;
        this.ingredients = new HashMap<>();
    }

    // Constructor de copia profunda (Copy Constructor)
    public Recipe(Recipe recipe) {
        this.name = recipe.name;
        this.category = recipe.category;
        this.preparationTime = recipe.preparationTime;
        this.cookingTime = recipe.cookingTime;
        this.servings = recipe.servings;
        this.ingredients = new HashMap<>(recipe.ingredients);
        this.instructions = recipe.instructions;
    }

    /**
     * Método de copia que usa el copy constructor
     * Mantiene compatibilidad con código existente
     */
    public Recipe copy() {
        return new Recipe(this);
    }

    public Recipe cloneWithModifications(String newName, int newServings) {
        Recipe cloned = copy();
        cloned.name = newName;
        cloned.servings = newServings;
        return cloned;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Map<String, String> getIngredients() {
        return new HashMap<>(ingredients);
    }

    public void addIngredient(String ingredient, String quantity) {
        this.ingredients.put(ingredient, quantity);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RECETA: ").append(name).append(" =====\n");
        sb.append("Categoría: ").append(category).append("\n");
        sb.append("Porciones: ").append(servings).append("\n");
        sb.append("Tiempo de preparación: ").append(preparationTime).append(" min\n");
        sb.append("Tiempo de cocción: ").append(cookingTime).append(" min\n");
        sb.append("\nIngredientes:\n");
        ingredients.forEach((ing, qty) -> 
            sb.append("  - ").append(ing).append(": ").append(qty).append("\n"));
        sb.append("\nInstrucciones: ").append(instructions);
        return sb.toString();
    }
}
