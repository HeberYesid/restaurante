package com.example.patterns.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro de prototipos de recetas
 */
public class RecipeRegistry {
    private Map<String, Recipe> recipes = new HashMap<>();

    public void addRecipe(String key, Recipe recipe) {
        recipes.put(key, recipe);
    }

    public Recipe getRecipe(String key) {
        Recipe prototype = recipes.get(key);
        return prototype != null ? prototype.copy() : null;
    }

    public void initializeDefaultRecipes() {
        // Receta de Pizza
        Recipe pizza = new Recipe("Pizza Margherita Clásica", "Italiana");
        pizza.setPreparationTime(20);
        pizza.setCookingTime(15);
        pizza.setServings(4);
        pizza.addIngredient("Harina", "500g");
        pizza.addIngredient("Levadura", "10g");
        pizza.addIngredient("Tomate", "400g");
        pizza.addIngredient("Mozzarella", "300g");
        pizza.addIngredient("Albahaca", "20g");
        pizza.addIngredient("Aceite de oliva", "50ml");
        pizza.setInstructions("Preparar la masa, extender, agregar ingredientes y hornear a 220°C");
        addRecipe("pizza", pizza);

        // Receta de Pasta
        Recipe pasta = new Recipe("Pasta Carbonara Original", "Italiana");
        pasta.setPreparationTime(10);
        pasta.setCookingTime(15);
        pasta.setServings(4);
        pasta.addIngredient("Spaghetti", "400g");
        pasta.addIngredient("Huevos", "4 unidades");
        pasta.addIngredient("Panceta", "200g");
        pasta.addIngredient("Queso Pecorino", "100g");
        pasta.addIngredient("Pimienta negra", "al gusto");
        pasta.setInstructions("Cocinar pasta, freír panceta, mezclar con huevos y queso");
        addRecipe("pasta", pasta);

        // Receta de Ensalada
        Recipe salad = new Recipe("Ensalada César", "Ensaladas");
        salad.setPreparationTime(15);
        salad.setCookingTime(0);
        salad.setServings(2);
        salad.addIngredient("Lechuga romana", "1 unidad");
        salad.addIngredient("Pollo", "200g");
        salad.addIngredient("Pan", "100g");
        salad.addIngredient("Queso parmesano", "50g");
        salad.addIngredient("Aderezo césar", "100ml");
        salad.setInstructions("Lavar lechuga, asar pollo, tostar pan, mezclar con aderezo");
        addRecipe("salad", salad);
    }
}
