package com.example.patterns.behavioral.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Patrón Visitor - Tests de Cobertura")
class VisitorPatternTest {

    @Nested
    @DisplayName("PriceCalculatorVisitor Tests")
    class PriceCalculatorTests {

        @Test
        @DisplayName("Debe calcular precio de entrada sin descuento")
        void shouldCalculateAppetizerPrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            Appetizer appetizer = new Appetizer("Bruschetta", 8.50, false);

            // When
            visitor.visit(appetizer);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(8.50);
        }

        @Test
        @DisplayName("Debe aplicar descuento del 10% a entradas compartidas")
        void shouldApplyDiscountToSharedAppetizer() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            Appetizer appetizer = new Appetizer("Nachos", 10.00, true);

            // When
            visitor.visit(appetizer);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(9.00); // 10% descuento
        }

        @Test
        @DisplayName("Debe calcular precio de plato principal")
        void shouldCalculateMainCoursePrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            MainCourse mainCourse = new MainCourse("Filete", 25.00, 800);

            // When
            visitor.visit(mainCourse);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(25.00);
        }

        @Test
        @DisplayName("Debe calcular precio de postre")
        void shouldCalculateDessertPrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            Dessert dessert = new Dessert("Tiramisú", 7.50, 35);

            // When
            visitor.visit(dessert);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(7.50);
        }

        @Test
        @DisplayName("Debe calcular precio de bebida")
        void shouldCalculateDrinkPrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            Drink drink = new Drink("Limonada", 3.50, 500);

            // When
            visitor.visit(drink);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(3.50);
        }

        @Test
        @DisplayName("Debe calcular precio total de menú completo")
        void shouldCalculateCompleteMenuPrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            
            Appetizer appetizer = new Appetizer("Ensalada", 6.00, false);
            MainCourse mainCourse = new MainCourse("Pasta", 15.00, 650);
            Dessert dessert = new Dessert("Helado", 5.00, 20);
            Drink drink = new Drink("Vino", 8.00, 250);

            // When
            visitor.visit(appetizer);
            visitor.visit(mainCourse);
            visitor.visit(dessert);
            visitor.visit(drink);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(34.00);
        }

        @Test
        @DisplayName("Debe resetear el precio total")
        void shouldResetTotalPrice() {
            // Given
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();
            visitor.visit(new Drink("Agua", 2.00, 330));

            // When
            visitor.reset();

            // Then
            assertThat(visitor.getTotalPrice()).isZero();
        }
    }

    @Nested
    @DisplayName("NutritionalAnalysisVisitor Tests")
    class NutritionalAnalysisTests {

        @Test
        @DisplayName("Debe analizar calorías de plato principal")
        void shouldAnalyzeMainCourseCalories() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            MainCourse mainCourse = new MainCourse("Hamburguesa", 12.00, 950);

            // When
            visitor.visit(mainCourse);

            // Then
            assertThat(visitor.getTotalCalories()).isEqualTo(950);
        }

        @Test
        @DisplayName("Debe analizar azúcar del postre")
        void shouldAnalyzeDessertSugar() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            Dessert dessert = new Dessert("Brownie", 6.50, 45);

            // When
            visitor.visit(dessert);

            // Then
            assertThat(visitor.getTotalSugar()).isEqualTo(45);
        }

        @Test
        @DisplayName("Debe analizar entrada")
        void shouldAnalyzeAppetizer() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            Appetizer appetizer = new Appetizer("Alitas", 9.00, false);

            // When
            visitor.visit(appetizer);

            // Then
            // Solo verifica que no lance excepción
            assertThat(visitor.getTotalCalories()).isZero(); // Appetizer no suma calorías
        }

        @Test
        @DisplayName("Debe analizar bebida")
        void shouldAnalyzeDrink() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            Drink drink = new Drink("Coca-Cola", 2.50, 355);

            // When
            visitor.visit(drink);

            // Then
            // Solo verifica que no lance excepción
            assertThat(visitor.getTotalSugar()).isZero(); // Drink no tiene tracking de azúcar en este visitor
        }

        @Test
        @DisplayName("Debe sumar calorías y azúcares totales")
        void shouldSumTotalNutrition() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            
            MainCourse main1 = new MainCourse("Pizza", 14.00, 800);
            MainCourse main2 = new MainCourse("Pasta", 13.00, 700);
            Dessert dessert1 = new Dessert("Flan", 5.00, 30);
            Dessert dessert2 = new Dessert("Tarta", 7.00, 40);

            // When
            visitor.visit(main1);
            visitor.visit(main2);
            visitor.visit(dessert1);
            visitor.visit(dessert2);

            // Then
            assertThat(visitor.getTotalCalories()).isEqualTo(1500);
            assertThat(visitor.getTotalSugar()).isEqualTo(70);
        }

        @Test
        @DisplayName("Debe resetear análisis nutricional")
        void shouldResetNutritionalData() {
            // Given
            NutritionalAnalysisVisitor visitor = new NutritionalAnalysisVisitor();
            visitor.visit(new MainCourse("Test", 10.00, 500));
            visitor.visit(new Dessert("Test", 5.00, 25));

            // When
            visitor.reset();

            // Then
            assertThat(visitor.getTotalCalories()).isZero();
            assertThat(visitor.getTotalSugar()).isZero();
        }
    }

    @Nested
    @DisplayName("MenuItem Tests")
    class MenuItemTests {

        @Test
        @DisplayName("Appetizer debe aceptar visitor")
        void appetizerShouldAcceptVisitor() {
            // Given
            Appetizer appetizer = new Appetizer("Ceviche", 12.00, false);
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();

            // When
            appetizer.accept(visitor);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(12.00);
        }

        @Test
        @DisplayName("MainCourse debe aceptar visitor")
        void mainCourseShouldAcceptVisitor() {
            // Given
            MainCourse mainCourse = new MainCourse("Salmón", 22.00, 650);
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();

            // When
            mainCourse.accept(visitor);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(22.00);
        }

        @Test
        @DisplayName("Dessert debe aceptar visitor")
        void dessertShouldAcceptVisitor() {
            // Given
            Dessert dessert = new Dessert("Panna Cotta", 6.00, 28);
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();

            // When
            dessert.accept(visitor);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(6.00);
        }

        @Test
        @DisplayName("Drink debe aceptar visitor")
        void drinkShouldAcceptVisitor() {
            // Given
            Drink drink = new Drink("Mojito", 9.00, 300);
            PriceCalculatorVisitor visitor = new PriceCalculatorVisitor();

            // When
            drink.accept(visitor);

            // Then
            assertThat(visitor.getTotalPrice()).isEqualTo(9.00);
        }
    }
}
