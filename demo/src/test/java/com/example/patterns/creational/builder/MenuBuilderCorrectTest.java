package com.example.patterns.creational.builder;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Tests para Builder Pattern con implementación correcta
 * Demuestra testing completo del patrón Builder
 */
@DisplayName("Builder Pattern - Menu Correct Tests")
class MenuBuilderCorrectTest {

    @Nested
    @DisplayName("Menu Builder Basic Tests")
    class BasicBuilderTests {

        @Test
        @DisplayName("Debe lanzar excepción si el menú está completamente vacío")
        void testBuildEmptyMenu() {
            // Arrange & Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Menu.Builder()
                    .name("Menú Vacío")
                    .build();
            }, "Debe lanzar IllegalStateException cuando el menú no tiene items");
        }

        @Test
        @DisplayName("Debe construir menú con todos los componentes")
        void testBuildCompleteMenu() {
            // Act
            Menu menu = new Menu.Builder()
                .name("Menú Completo")
                .addAppetizer("Ensalada César", 8.50)
                .addMainCourse("Filete con papas", 22.99)
                .addDessert("Tiramisú", 6.50)
                .addBeverage("Vino tinto", 15.00)
                .vegetarian(false)
                .glutenFree(false)
                .specialInstructions("Sin sal extra")
                .build();

            // Assert
            assertAll("Complete menu validations",
                () -> assertEquals("Menú Completo", menu.getName()),
                () -> assertThat(menu.getAppetizers()).hasSize(1),
                () -> assertThat(menu.getMainCourses()).hasSize(1),
                () -> assertThat(menu.getDesserts()).hasSize(1),
                () -> assertThat(menu.getBeverages()).hasSize(1),
                () -> assertEquals(52.99, menu.getTotalPrice(), 0.01),
                () -> assertFalse(menu.isVegetarian()),
                () -> assertFalse(menu.isGlutenFree()),
                () -> assertEquals("Sin sal extra", menu.getSpecialInstructions())
            );
        }

        @Test
        @DisplayName("Debe calcular precio total correctamente")
        void testPriceCalculation() {
            // Act
            Menu menu = new Menu.Builder()
                .name("Test Precio")
                .addAppetizer("Item 1", 10.00)
                .addMainCourse("Item 2", 20.00)
                .addDessert("Item 3", 5.00)
                .addBeverage("Item 4", 3.50)
                .build();

            // Assert
            assertEquals(38.50, menu.getTotalPrice(), 0.01);
        }

        @Test
        @DisplayName("Debe permitir múltiples items del mismo tipo")
        void testMultipleItemsSameType() {
            // Act
            Menu menu = new Menu.Builder()
                .name("Menú Multi")
                .addAppetizer("Entrada 1", 5.00)
                .addAppetizer("Entrada 2", 6.00)
                .addAppetizer("Entrada 3", 7.00)
                .build();

            // Assert
            assertThat(menu.getAppetizers()).hasSize(3);
            assertEquals(18.00, menu.getTotalPrice(), 0.01);
        }
    }

    @Nested
    @DisplayName("Menu Director Tests")
    class DirectorTests {

        private MenuDirector director;

        @BeforeEach
        void setUp() {
            director = new MenuDirector();
        }

        @Test
        @DisplayName("Debe crear menú familiar")
        void testFamilyMenu() {
            // Act
            Menu menu = director.createFamilyMenu();

            // Assert
            assertAll("Family menu validations",
                () -> assertEquals("Menú Familiar", menu.getName()),
                () -> assertThat(menu.getAppetizers()).isNotEmpty(),
                () -> assertThat(menu.getMainCourses()).isNotEmpty(),
                () -> assertThat(menu.getDesserts()).isNotEmpty(),
                () -> assertThat(menu.getBeverages()).isNotEmpty(),
                () -> assertTrue(menu.getTotalPrice() > 0)
            );
        }

        @Test
        @DisplayName("Debe crear menú vegetariano")
        void testVegetarianMenu() {
            // Act
            Menu menu = director.createVegetarianMenu();

            // Assert
            assertAll("Vegetarian menu validations",
                () -> assertEquals("Menú Vegetariano", menu.getName()),
                () -> assertTrue(menu.isVegetarian()),
                () -> assertThat(menu.getSpecialInstructions()).isNotEmpty()
            );
        }

        @Test
        @DisplayName("Debe crear menú gourmet")
        void testGourmetMenu() {
            // Act
            Menu menu = director.createGourmetMenu();

            // Assert
            assertAll("Gourmet menu validations",
                () -> assertEquals("Menú Gourmet", menu.getName()),
                () -> assertThat(menu.getAppetizers()).hasSizeGreaterThanOrEqualTo(1),
                () -> assertTrue(menu.getTotalPrice() > 100) // Menú gourmet es caro
            );
        }

        @Test
        @DisplayName("Debe crear menú saludable")
        void testHealthyMenu() {
            // Act
            Menu menu = director.createHealthyMenu();

            // Assert
            assertAll("Healthy menu validations",
                () -> assertEquals("Menú Saludable", menu.getName()),
                () -> assertTrue(menu.isGlutenFree()),
                () -> assertFalse(menu.isVegetarian())
            );
        }

        @ParameterizedTest(name = "Director menu {0} debe ser válido")
        @ValueSource(strings = {"FAMILY", "VEGETARIAN", "GOURMET", "HEALTHY"})
        void testAllDirectorMenus(String menuType) {
            // Act
            Menu menu = switch (menuType) {
                case "FAMILY" -> director.createFamilyMenu();
                case "VEGETARIAN" -> director.createVegetarianMenu();
                case "GOURMET" -> director.createGourmetMenu();
                case "HEALTHY" -> director.createHealthyMenu();
                default -> throw new IllegalArgumentException("Unknown type");
            };

            // Assert
            assertAll(
                () -> assertNotNull(menu),
                () -> assertThat(menu.getName()).isNotEmpty(),
                () -> assertTrue(menu.getTotalPrice() > 0)
            );
        }
    }

    @Nested
    @DisplayName("Immutability Tests")
    class ImmutabilityTests {

        @Test
        @DisplayName("Las listas devueltas deben ser copias defensivas")
        void testDefensiveCopies() {
            // Arrange
            Menu menu = new Menu.Builder()
                .name("Test Inmutabilidad")
                .addAppetizer("Entrada 1", 5.00)
                .build();

            // Act - Intentar modificar lista devuelta
            var appetizers = menu.getAppetizers();
            appetizers.add("Nuevo item");

            // Assert - El menú original no debe cambiar
            assertThat(menu.getAppetizers()).hasSize(1);
        }

        @Test
        @DisplayName("Builder debe ser reusable")
        void testBuilderReusability() {
            // Arrange
            Menu.Builder builder = new Menu.Builder()
                .name("Base");

            // Act
            Menu menu1 = builder.addAppetizer("Item 1", 5.00).build();
            Menu menu2 = builder.addAppetizer("Item 2", 6.00).build();

            // Assert
            assertNotSame(menu1, menu2);
            // Nota: En esta implementación el builder acumula estado
            assertThat(menu2.getAppetizers()).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Debe manejar nombre null")
        void testNullName() {
            // Act
            Menu menu = new Menu.Builder()
                .name(null)
                .addAppetizer("Test", 1.0)
                .build();

            // Assert
            assertNull(menu.getName());
        }

        @Test
        @DisplayName("Debe manejar precios cero")
        void testZeroPrice() {
            // Act
            Menu menu = new Menu.Builder()
                .name("Gratis")
                .addAppetizer("Free item", 0.0)
                .build();

            // Assert
            assertEquals(0.0, menu.getTotalPrice());
        }

        @Test
        @DisplayName("Debe manejar instrucciones especiales vacías")
        void testEmptyInstructions() {
            // Act
            Menu menu = new Menu.Builder()
                .name("Test")
                .addMainCourse("Plato", 5.0)
                .specialInstructions("")
                .build();

            // Assert
            assertEquals("", menu.getSpecialInstructions());
        }

        @Test
        @DisplayName("Debe manejar múltiples configuraciones de atributos booleanos")
        void testBooleanAttributes() {
            // Act
            Menu veganMenu = new Menu.Builder()
                .name("Vegano")
                .addMainCourse("Ensalada", 10.0)
                .vegetarian(true)
                .glutenFree(true)
                .build();

            Menu normalMenu = new Menu.Builder()
                .name("Normal")
                .addMainCourse("Hamburguesa", 12.0)
                .vegetarian(false)
                .glutenFree(false)
                .build();

            // Assert
            assertTrue(veganMenu.isVegetarian() && veganMenu.isGlutenFree());
            assertFalse(normalMenu.isVegetarian() || normalMenu.isGlutenFree());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Debe crear múltiples menús con diferentes características")
        void testMultipleMenus() {
            // Act
            Menu budget = new Menu.Builder()
                .name("Económico")
                .addMainCourse("Plato del día", 8.99)
                .addBeverage("Agua", 1.00)
                .build();

            Menu premium = new Menu.Builder()
                .name("Premium")
                .addAppetizer("Caviar", 50.00)
                .addMainCourse("Langosta", 75.00)
                .addDessert("Tarta especial", 25.00)
                .addBeverage("Champagne", 80.00)
                .build();

            // Assert
            assertThat(budget.getTotalPrice()).isLessThan(premium.getTotalPrice());
            assertThat(premium.getTotalPrice()).isGreaterThan(200);
        }

        @Test
        @DisplayName("Debe construir menú similar al Director")
        void testManualVsDirector() {
            // Arrange
            MenuDirector director = new MenuDirector();

            // Act
            Menu directorMenu = director.createVegetarianMenu();
            
            Menu manualMenu = new Menu.Builder()
                .name("Mi Menú Vegetariano")
                .vegetarian(true)
                .addAppetizer("Ensalada", 7.00)
                .build();

            // Assert
            assertTrue(directorMenu.isVegetarian());
            assertTrue(manualMenu.isVegetarian());
        }
    }

    @Nested
    @DisplayName("ToString Tests")
    class ToStringTests {

        @Test
        @DisplayName("ToString debe incluir información relevante")
        void testToStringContent() {
            // Arrange
            Menu menu = new Menu.Builder()
                .name("Test Menu")
                .addAppetizer("Entrada", 5.00)
                .addMainCourse("Principal", 15.00)
                .specialInstructions("Sin picante")
                .build();

            // Act
            String menuString = menu.toString();

            // Assert
            assertThat(menuString)
                .contains("Test Menu")
                .contains("Entrada")
                .contains("Principal")
                .contains("Sin picante")
                .contains("20.00");
        }
    }
}
