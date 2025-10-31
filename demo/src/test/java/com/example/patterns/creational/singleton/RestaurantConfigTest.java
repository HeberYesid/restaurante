package com.example.patterns.creational.singleton;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Unitario para Singleton
 * Demuestra: Assertions básicas, verificación de instancia única
 */
@DisplayName("Singleton Pattern - RestaurantConfig Tests")
class RestaurantConfigTest {

    @BeforeEach
    void setUp() {
        // Resetear la tasa de impuesto al valor por defecto antes de cada test
        RestaurantConfig config = RestaurantConfig.getInstance();
        config.setTaxRate(0.16); // 16% IVA por defecto
    }

    @Test
    @DisplayName("Debe retornar la misma instancia (Singleton)")
    void testSingletonInstance() {
        // Arrange & Act
        RestaurantConfig instance1 = RestaurantConfig.getInstance();
        RestaurantConfig instance2 = RestaurantConfig.getInstance();

        // Assert
        assertSame(instance1, instance2, "Deben ser la misma instancia");
        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("Debe generar números de orden secuenciales")
    void testOrderNumberGeneration() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();

        // Act
        int order1 = config.getNextOrderNumber();
        int order2 = config.getNextOrderNumber();
        int order3 = config.getNextOrderNumber();

        // Assert
        assertTrue(order2 > order1, "El segundo número debe ser mayor");
        assertTrue(order3 > order2, "El tercer número debe ser mayor");
        assertEquals(1, order2 - order1, "Deben ser consecutivos");
    }

    @Test
    @DisplayName("Debe calcular impuestos correctamente")
    void testTaxCalculation() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        double amount = 100.0;

        // Act
        double tax = config.calculateTax(amount);
        double total = config.calculateTotal(amount);

        // Assert
        assertEquals(16.0, tax, 0.01, "Impuesto debe ser 16%");
        assertEquals(116.0, total, 0.01, "Total debe incluir impuesto");
        assertThat(tax).isCloseTo(16.0, org.assertj.core.data.Offset.offset(0.01));
    }

    @Test
    @DisplayName("Debe tener configuración por defecto")
    void testDefaultConfiguration() {
        // Arrange & Act
        RestaurantConfig config = RestaurantConfig.getInstance();

        // Assert
        assertNotNull(config.getRestaurantName());
        assertNotNull(config.getAddress());
        assertTrue(config.getMaxTables() > 0);
        assertFalse(config.getPaymentMethods().isEmpty());
    }

    @Test
    @DisplayName("Debe permitir agregar método de pago")
    void testAddPaymentMethod() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        int initialSize = config.getPaymentMethods().size();

        // Act
        config.addPaymentMethod("Criptomonedas");
        
        // Assert
        assertEquals(initialSize + 1, config.getPaymentMethods().size());
        assertTrue(config.getPaymentMethods().contains("Criptomonedas"));
    }
}
