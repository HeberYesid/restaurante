package com.example.patterns.creational.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para RecipeRegistry (Patrón Prototype Registry)
 */
class RecipeRegistryTest {

    private RecipeRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new RecipeRegistry();
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {

        @Test
        @DisplayName("Debe registrar una receta")
        void shouldaddRecipe() {
            Recipe recipe = new Recipe("Pasta", "Italian");
            recipe.setPreparationTime(10);
            recipe.setCookingTime(15);

            registry.addRecipe("basic-pasta", recipe);

            Recipe retrieved = registry.getRecipe("basic-pasta");
            assertThat(retrieved).isNotNull();
            assertThat(retrieved.getName()).isEqualTo("Pasta");
        }

        @Test
        @DisplayName("Debe permitir registrar múltiples recetas")
        void shouldAllowRegisteringMultipleRecipes() {
            Recipe pasta = new Recipe("Pasta", "Italian");
            Recipe salad = new Recipe("Salad", "Healthy");
            Recipe dessert = new Recipe("Tiramisu", "Dessert");

            registry.addRecipe("pasta", pasta);
            registry.addRecipe("salad", salad);
            registry.addRecipe("dessert", dessert);

            assertThat(registry.getRecipe("pasta")).isNotNull();
            assertThat(registry.getRecipe("salad")).isNotNull();
            assertThat(registry.getRecipe("dessert")).isNotNull();
        }

        @Test
        @DisplayName("Debe sobrescribir receta si se registra con la misma key")
        void shouldOverwriteRecipeIfSameKeyUsed() {
            Recipe original = new Recipe("Pizza Margherita", "Italian");
            Recipe updated = new Recipe("Pizza Napolitana", "Italian");

            registry.addRecipe("pizza", original);
            registry.addRecipe("pizza", updated);

            Recipe retrieved = registry.getRecipe("pizza");
            assertThat(retrieved.getName()).isEqualTo("Pizza Napolitana");
        }
    }

    @Nested
    @DisplayName("Get Recipe Tests")
    class GetRecipeTests {

        @Test
        @DisplayName("Debe retornar copia de la receta registrada")
        void shouldReturnCopyOfRegisteredRecipe() {
            Recipe original = new Recipe("Risotto", "Italian");
            original.setServings(4);
            original.addIngredient("Rice", "200g");

            registry.addRecipe("risotto", original);
            Recipe copy = registry.getRecipe("risotto");

            assertThat(copy).isNotSameAs(original);
            assertThat(copy.getName()).isEqualTo(original.getName());
            assertThat(copy.getServings()).isEqualTo(original.getServings());
        }

        @Test
        @DisplayName("Debe retornar null si la receta no existe")
        void shouldReturnNullIfRecipeDoesNotExist() {
            Recipe result = registry.getRecipe("non-existent");
            
            assertThat(result).isNull();
        }

        @Test
        @DisplayName("Modificar la copia no debe afectar el prototipo")
        void modifyingCopyShouldNotAffectPrototype() {
            Recipe original = new Recipe("Spaghetti", "Italian");
            original.setServings(2);

            registry.addRecipe("spaghetti", original);
            
            Recipe copy1 = registry.getRecipe("spaghetti");
            copy1.setServings(4);
            
            Recipe copy2 = registry.getRecipe("spaghetti");
            
            assertThat(copy2.getServings()).isEqualTo(2);
        }

        @Test
        @DisplayName("Debe permitir obtener la misma receta múltiples veces")
        void shouldAllowGettingSameRecipeMultipleTimes() {
            Recipe recipe = new Recipe("Lasagna", "Italian");
            registry.addRecipe("lasagna", recipe);

            Recipe copy1 = registry.getRecipe("lasagna");
            Recipe copy2 = registry.getRecipe("lasagna");
            Recipe copy3 = registry.getRecipe("lasagna");

            assertThat(copy1).isNotSameAs(copy2);
            assertThat(copy2).isNotSameAs(copy3);
            assertThat(copy1).isNotSameAs(copy3);
            
            assertThat(copy1.getName()).isEqualTo(copy2.getName());
            assertThat(copy2.getName()).isEqualTo(copy3.getName());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Debe funcionar como registro de prototipos")
        void shouldWorkAsPrototypeRegistry() {
            // Registrar prototipos base
            Recipe italianBase = new Recipe("Italian Base", "Italian");
            italianBase.addIngredient("Olive Oil", "50ml");
            italianBase.addIngredient("Garlic", "2 cloves");

            Recipe healthyBase = new Recipe("Healthy Base", "Healthy");
            healthyBase.addIngredient("Vegetables", "300g");
            healthyBase.addIngredient("Olive Oil", "20ml");

            registry.addRecipe("italian-base", italianBase);
            registry.addRecipe("healthy-base", healthyBase);

            // Crear variaciones a partir de los prototipos
            Recipe pasta = registry.getRecipe("italian-base");
            pasta.addIngredient("Pasta", "200g");
            pasta.addIngredient("Tomatoes", "400g");

            Recipe salad = registry.getRecipe("healthy-base");
            salad.addIngredient("Lettuce", "150g");
            salad.addIngredient("Tomatoes", "100g");

            // Verificar que las variaciones son independientes
            assertThat(pasta.getIngredients()).hasSize(4);
            assertThat(salad.getIngredients()).hasSize(4);

            // Verificar que los prototipos no fueron modificados
            Recipe italianBaseCheck = registry.getRecipe("italian-base");
            assertThat(italianBaseCheck.getIngredients()).hasSize(2);
        }

        @Test
        @DisplayName("Debe soportar clonación de recetas complejas")
        void shouldSupportCloningComplexRecipes() {
            Recipe complexRecipe = new Recipe("Paella Valenciana", "Spanish");
            complexRecipe.setPreparationTime(30);
            complexRecipe.setCookingTime(45);
            complexRecipe.setServings(6);
            complexRecipe.addIngredient("Rice", "400g");
            complexRecipe.addIngredient("Chicken", "500g");
            complexRecipe.addIngredient("Seafood Mix", "300g");
            complexRecipe.addIngredient("Saffron", "1g");
            complexRecipe.addIngredient("Bell Peppers", "200g");
            complexRecipe.setInstructions("1. Sauté chicken\n2. Add rice\n3. Add seafood\n4. Simmer until done");

            registry.addRecipe("paella", complexRecipe);

            Recipe clone = registry.getRecipe("paella");

            assertThat(clone.getName()).isEqualTo("Paella Valenciana");
            assertThat(clone.getPreparationTime()).isEqualTo(30);
            assertThat(clone.getCookingTime()).isEqualTo(45);
            assertThat(clone.getServings()).isEqualTo(6);
            assertThat(clone.getIngredients()).hasSize(5);
            assertThat(clone.getInstructions()).contains("Sauté chicken");
        }
    }
}
