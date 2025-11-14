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

    @Test
    @DisplayName("Debe modificar nombre del restaurante")
    void testSetRestaurantName() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        String newName = "El Nuevo Sabor";
        
        // Act
        config.setRestaurantName(newName);
        
        // Assert
        assertThat(config.getRestaurantName()).isEqualTo(newName);
    }

    @Test
    @DisplayName("Debe modificar dirección del restaurante")
    void testSetAddress() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        String newAddress = "Calle Secundaria 456";
        
        // Act
        config.setAddress(newAddress);
        
        // Assert
        assertThat(config.getAddress()).isEqualTo(newAddress);
    }

    @Test
    @DisplayName("Debe modificar número máximo de mesas")
    void testSetMaxTables() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        int newMaxTables = 50;
        
        // Act
        config.setMaxTables(newMaxTables);
        
        // Assert
        assertThat(config.getMaxTables()).isEqualTo(newMaxTables);
    }

    @Test
    @DisplayName("Debe modificar tasa de impuesto")
    void testSetTaxRate() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        double newTaxRate = 0.19; // 19%
        
        // Act
        config.setTaxRate(newTaxRate);
        
        // Assert
        assertThat(config.getTaxRate()).isEqualTo(newTaxRate);
        
        // Verificar que el cálculo usa la nueva tasa
        double tax = config.calculateTax(100.0);
        assertThat(tax).isCloseTo(19.0, org.assertj.core.data.Offset.offset(0.01));
    }

    @Test
    @DisplayName("Debe retornar copia defensiva de métodos de pago")
    void testGetPaymentMethodsDefensiveCopy() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        
        // Act
        java.util.List<String> methods1 = config.getPaymentMethods();
        java.util.List<String> methods2 = config.getPaymentMethods();
        
        // Assert - Deben ser objetos diferentes (copia defensiva)
        assertThat(methods1).isNotSameAs(methods2);
        assertThat(methods1).isEqualTo(methods2);
    }

    @Test
    @DisplayName("No debe agregar método de pago duplicado")
    void testAddDuplicatePaymentMethod() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        config.addPaymentMethod("PayPal");
        int sizeAfterFirst = config.getPaymentMethods().size();
        
        // Act - Intentar agregar el mismo método
        config.addPaymentMethod("PayPal");
        
        // Assert - El tamaño no debe cambiar
        assertThat(config.getPaymentMethods().size()).isEqualTo(sizeAfterFirst);
    }

    @Test
    @DisplayName("Debe modificar horario de apertura")
    void testSetOpeningTime() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        java.time.LocalDateTime newOpeningTime = java.time.LocalDateTime.now().withHour(10).withMinute(30);
        
        // Act
        config.setOpeningTime(newOpeningTime);
        
        // Assert
        assertThat(config.getOpeningTime().toLocalTime())
            .isEqualTo(newOpeningTime.toLocalTime());
    }

    @Test
    @DisplayName("Debe modificar horario de cierre")
    void testSetClosingTime() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        java.time.LocalDateTime newClosingTime = java.time.LocalDateTime.now().withHour(22).withMinute(45);
        
        // Act
        config.setClosingTime(newClosingTime);
        
        // Assert
        assertThat(config.getClosingTime().toLocalTime())
            .isEqualTo(newClosingTime.toLocalTime());
    }

    @Test
    @DisplayName("Debe retornar copia de horarios (inmutabilidad)")
    void testGetTimesDefensiveCopy() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        java.time.LocalDateTime originalOpening = config.getOpeningTime();
        
        // Act - Modificar la copia obtenida
        java.time.LocalDateTime modifiedOpening = originalOpening.plusHours(2);
        
        // Assert - El original no debe cambiar
        assertThat(config.getOpeningTime()).isNotEqualTo(modifiedOpening);
    }

    @Test
    @DisplayName("Debe generar toString con toda la información")
    void testToString() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        
        // Act
        String configString = config.toString();
        
        // Assert
        assertThat(configString).contains("CONFIGURACIÓN DEL RESTAURANTE");
        assertThat(configString).contains(config.getRestaurantName());
        assertThat(configString).contains(config.getAddress());
        assertThat(configString).contains("Mesas disponibles");
        assertThat(configString).contains("Horario");
        assertThat(configString).contains("Métodos de pago");
        assertThat(configString).contains("Tasa de impuesto");
    }

    @Test
    @DisplayName("Debe calcular total con diferentes montos")
    void testCalculateTotalWithDifferentAmounts() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        config.setTaxRate(0.16);
        
        // Act & Assert
        assertThat(config.calculateTotal(100.0)).isCloseTo(116.0, org.assertj.core.data.Offset.offset(0.01));
        assertThat(config.calculateTotal(50.0)).isCloseTo(58.0, org.assertj.core.data.Offset.offset(0.01));
        assertThat(config.calculateTotal(0.0)).isCloseTo(0.0, org.assertj.core.data.Offset.offset(0.01));
    }

    @Test
    @DisplayName("Debe prevenir clonación del Singleton")
    void testCloneNotSupported() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        
        // Act & Assert
        assertThrows(CloneNotSupportedException.class, () -> {
            config.clone();
        });
    }

    @Test
    @DisplayName("Debe agregar múltiples métodos de pago")
    void testAddMultiplePaymentMethods() {
        // Arrange
        RestaurantConfig config = RestaurantConfig.getInstance();
        int initialSize = config.getPaymentMethods().size();
        
        // Act
        config.addPaymentMethod("Apple Pay");
        config.addPaymentMethod("Google Pay");
        config.addPaymentMethod("Samsung Pay");
        
        // Assert
        assertThat(config.getPaymentMethods().size()).isEqualTo(initialSize + 3);
        assertThat(config.getPaymentMethods()).contains("Apple Pay", "Google Pay", "Samsung Pay");
    }
}
