package com.example.patterns.structural.adapter;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test con Mockito - Adapter Pattern
 * Demuestra: Mock, verify, when-then
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Adapter Pattern - Payment Adapter Tests")
class PaymentAdapterTest {

    @Mock
    private LegacyPOSSystem mockLegacySystem;

    @InjectMocks
    private PaymentAdapter adapter;

    @Test
    @DisplayName("Debe validar tarjeta usando sistema legacy (Mock)")
    void testValidatePaymentMethod_WithMock() {
        // Arrange
        PaymentRequest request = new PaymentRequest("4532123456789012", 100.0, "Test User");
        when(mockLegacySystem.validateCardOldFormat(anyString())).thenReturn(true);

        // Act
        boolean result = adapter.validatePaymentMethod(request);

        // Assert
        assertTrue(result);
        verify(mockLegacySystem).validateCardOldFormat("4532123456789012");
    }

    @Test
    @DisplayName("Debe procesar pago y retornar resultado (Mock)")
    void testProcessPayment_WithMock() {
        // Arrange
        PaymentRequest request = new PaymentRequest("4532123456789012", 125.50, "Juan Pérez");
        
        when(mockLegacySystem.processPaymentOldFormat(anyString(), anyString(), anyString()))
            .thenAnswer(invocation -> {
                String amount = invocation.getArgument(1);
                return "LEGACY: Procesando pago de " + amount + " con tarjeta ****-****-****-9012 en fecha";
            });

        // Act
        PaymentResult result = adapter.processPayment(request);

        // Assert
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertNotNull(result.getTransactionId());
        assertThat(result.getMessage()).contains("LEGACY");
        
        // Verify interactions
        verify(mockLegacySystem, times(1))
            .processPaymentOldFormat(eq("4532123456789012"), eq("125.50"), anyString());
    }

    @Test
    @DisplayName("Debe rechazar tarjeta inválida")
    void testValidateInvalidCard() {
        // Arrange
        PaymentRequest request = new PaymentRequest("123", 100.0, "Test User");
        when(mockLegacySystem.validateCardOldFormat("123")).thenReturn(false);

        // Act
        boolean result = adapter.validatePaymentMethod(request);

        // Assert
        assertFalse(result);
        verify(mockLegacySystem).validateCardOldFormat("123");
    }

    @Nested
    @DisplayName("Tests con sistema legacy real (sin mock)")
    class RealSystemTests {
        
        @Test
        @DisplayName("Debe funcionar con sistema legacy real")
        void testWithRealLegacySystem() {
            // Arrange - usando sistema real, no mock
            LegacyPOSSystem realSystem = new LegacyPOSSystem();
            PaymentAdapter realAdapter = new PaymentAdapter(realSystem);
            PaymentRequest request = new PaymentRequest("4532123456789012", 50.0, "Cliente Real");

            // Act
            boolean isValid = realAdapter.validatePaymentMethod(request);
            PaymentResult result = realAdapter.processPayment(request);

            // Assert
            assertTrue(isValid);
            assertNotNull(result);
            assertTrue(result.isSuccess());
        }
    }
}
