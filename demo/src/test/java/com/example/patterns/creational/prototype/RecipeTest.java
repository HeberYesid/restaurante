package com.example.patterns.creational.prototype;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Recipe (Patrón Prototype)
 */
class RecipeTest {

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        
        @Test
        @DisplayName("Debe crear una receta con nombre y categoría")
        void shouldCreateRecipeWithNameAndCategory() {
            Recipe recipe = new Recipe("Pasta Carbonara", "Italian");
            
            assertThat(recipe).isNotNull();
            assertThat(recipe.getName()).isEqualTo("Pasta Carbonara");
            assertThat(recipe.getCategory()).isEqualTo("Italian");
        }

        @Test
        @DisplayName("Debe inicializar ingredientes vacíos")
        void shouldInitializeEmptyIngredients() {
            Recipe recipe = new Recipe("Pizza", "Italian");
            
            assertThat(recipe.getIngredients()).isEmpty();
        }
    }

    @Nested
    @DisplayName("Copy Constructor Tests")
    class CopyConstructorTests {

        @Test
        @DisplayName("Debe crear copia profunda con copy constructor")
        void shouldCreateDeepCopyWithCopyConstructor() {
            Recipe original = new Recipe("Lasagna", "Italian");
            original.setPreparationTime(30);
            original.setCookingTime(45);
            original.setServings(6);
            original.addIngredient("Pasta", "500g");
            original.addIngredient("Beef", "300g");
            original.setInstructions("Layer and bake");

            Recipe copy = new Recipe(original);

            assertThat(copy).isNotNull();
            assertThat(copy.getName()).isEqualTo(original.getName());
            assertThat(copy.getCategory()).isEqualTo(original.getCategory());
            assertThat(copy.getPreparationTime()).isEqualTo(original.getPreparationTime());
            assertThat(copy.getCookingTime()).isEqualTo(original.getCookingTime());
            assertThat(copy.getServings()).isEqualTo(original.getServings());
            assertThat(copy.getIngredients()).isEqualTo(original.getIngredients());
            assertThat(copy.getInstructions()).isEqualTo(original.getInstructions());
        }

        @Test
        @DisplayName("La copia debe ser independiente del original")
        void copyShouldBeIndependentFromOriginal() {
            Recipe original = new Recipe("Risotto", "Italian");
            original.addIngredient("Rice", "200g");
            
            Recipe copy = new Recipe(original);
            copy.addIngredient("Mushrooms", "100g");

            assertThat(original.getIngredients()).hasSize(1);
            assertThat(copy.getIngredients()).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Copy Method Tests")
    class CopyMethodTests {

        @Test
        @DisplayName("El método copy() debe crear una copia exacta")
        void copyMethodShouldCreateExactCopy() {
            Recipe original = new Recipe("Tiramisu", "Dessert");
            original.setPreparationTime(20);
            original.setCookingTime(0);
            original.setServings(8);
            original.addIngredient("Mascarpone", "500g");
            original.addIngredient("Espresso", "200ml");
            original.setInstructions("Layer and refrigerate");

            Recipe copy = original.copy();

            assertThat(copy).isNotSameAs(original);
            assertThat(copy.getName()).isEqualTo(original.getName());
            assertThat(copy.getCategory()).isEqualTo(original.getCategory());
            assertThat(copy.getPreparationTime()).isEqualTo(original.getPreparationTime());
            assertThat(copy.getCookingTime()).isEqualTo(original.getCookingTime());
            assertThat(copy.getServings()).isEqualTo(original.getServings());
            assertThat(copy.getIngredients()).isEqualTo(original.getIngredients());
            assertThat(copy.getInstructions()).isEqualTo(original.getInstructions());
        }

        @Test
        @DisplayName("Las copias deben ser independientes")
        void copiesShouldBeIndependent() {
            Recipe original = new Recipe("Pasta", "Italian");
            original.setServings(4);
            
            Recipe copy = original.copy();
            copy.setServings(8);

            assertThat(original.getServings()).isEqualTo(4);
            assertThat(copy.getServings()).isEqualTo(8);
        }
    }

    @Nested
    @DisplayName("Clone With Modifications Tests")
    class CloneWithModificationsTests {

        @Test
        @DisplayName("Debe clonar y modificar nombre y porciones")
        void shouldCloneAndModifyNameAndServings() {
            Recipe original = new Recipe("Pizza Margherita", "Italian");
            original.setServings(2);
            original.addIngredient("Mozzarella", "200g");

            Recipe modified = original.cloneWithModifications("Pizza Napolitana", 4);

            assertThat(modified.getName()).isEqualTo("Pizza Napolitana");
            assertThat(modified.getServings()).isEqualTo(4);
            assertThat(modified.getCategory()).isEqualTo(original.getCategory());
            assertThat(modified.getIngredients()).isEqualTo(original.getIngredients());
        }

        @Test
        @DisplayName("El original no debe ser afectado por las modificaciones")
        void originalShouldNotBeAffectedByModifications() {
            Recipe original = new Recipe("Spaghetti", "Italian");
            original.setServings(2);

            Recipe modified = original.cloneWithModifications("Fettuccine", 4);

            assertThat(original.getName()).isEqualTo("Spaghetti");
            assertThat(original.getServings()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("Setters and Getters Tests")
    class SettersAndGettersTests {

        @Test
        @DisplayName("Debe establecer y obtener tiempo de preparación")
        void shouldSetAndGetPreparationTime() {
            Recipe recipe = new Recipe("Test", "Test");
            recipe.setPreparationTime(15);
            
            assertThat(recipe.getPreparationTime()).isEqualTo(15);
        }

        @Test
        @DisplayName("Debe establecer y obtener tiempo de cocción")
        void shouldSetAndGetCookingTime() {
            Recipe recipe = new Recipe("Test", "Test");
            recipe.setCookingTime(30);
            
            assertThat(recipe.getCookingTime()).isEqualTo(30);
        }

        @Test
        @DisplayName("Debe establecer y obtener porciones")
        void shouldSetAndGetServings() {
            Recipe recipe = new Recipe("Test", "Test");
            recipe.setServings(6);
            
            assertThat(recipe.getServings()).isEqualTo(6);
        }

        @Test
        @DisplayName("Debe establecer y obtener instrucciones")
        void shouldSetAndGetInstructions() {
            Recipe recipe = new Recipe("Test", "Test");
            recipe.setInstructions("Mix and bake");
            
            assertThat(recipe.getInstructions()).isEqualTo("Mix and bake");
        }
    }

    @Nested
    @DisplayName("Ingredients Tests")
    class IngredientsTests {

        @Test
        @DisplayName("Debe agregar ingredientes")
        void shouldAddIngredients() {
            Recipe recipe = new Recipe("Pasta", "Italian");
            recipe.addIngredient("Tomatoes", "400g");
            recipe.addIngredient("Basil", "20g");
            
            assertThat(recipe.getIngredients()).hasSize(2);
            assertThat(recipe.getIngredients()).containsEntry("Tomatoes", "400g");
            assertThat(recipe.getIngredients()).containsEntry("Basil", "20g");
        }

        @Test
        @DisplayName("Debe retornar copia defensiva de ingredientes")
        void shouldReturnDefensiveCopyOfIngredients() {
            Recipe recipe = new Recipe("Salad", "Healthy");
            recipe.addIngredient("Lettuce", "100g");
            
            var ingredients = recipe.getIngredients();
            ingredients.put("Tomato", "50g");
            
            assertThat(recipe.getIngredients()).hasSize(1);
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("Debe generar toString con información completa")
        void shouldGenerateToStringWithCompleteInfo() {
            Recipe recipe = new Recipe("Paella", "Spanish");
            recipe.setPreparationTime(20);
            recipe.setCookingTime(40);
            recipe.setServings(4);
            recipe.addIngredient("Rice", "300g");
            recipe.addIngredient("Seafood", "500g");
            recipe.setInstructions("Cook rice with seafood");

            String result = recipe.toString();

            assertThat(result).contains("Paella");
            assertThat(result).contains("Spanish");
            assertThat(result).contains("20");
            assertThat(result).contains("40");
            assertThat(result).contains("4");
            assertThat(result).contains("Rice");
            assertThat(result).contains("300g");
            assertThat(result).contains("Seafood");
            assertThat(result).contains("500g");
            assertThat(result).contains("Cook rice with seafood");
        }
    }
}
