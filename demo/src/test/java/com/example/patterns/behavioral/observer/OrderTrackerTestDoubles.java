package com.example.patterns.behavioral.observer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test con todos los tipos de Test Doubles
 * Demuestra: Dummy, Fake, Stub, Spy, Mock
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Observer Pattern - OrderTracker Tests con Test Doubles")
class OrderTrackerTestDoubles {

    @Mock
    private OrderObserver mockObserver;

    @Spy
    private CustomerNotifier spyNotifier = new CustomerNotifier("Test Customer");

    @Captor
    private ArgumentCaptor<String> messageCaptor;

    // ==================== DUMMY ====================
    // Dummy: Objeto que se pasa pero nunca se usa realmente
    
    @Test
    @DisplayName("DUMMY: Observer que nunca se notifica")
    void testDummyObserver() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-001");
        OrderObserver dummyObserver = new OrderObserver() {
            @Override
            public void update(String orderId, String status, String message) {
                fail("Dummy no debería ser llamado");
            }

            @Override
            public String getObserverName() {
                return "Dummy Observer";
            }
        };

        // Act - No agregamos el dummy, solo lo creamos
        tracker.attach(mockObserver);
        tracker.updateStatus("CONFIRMADA", "Test message");

        // Assert - Dummy nunca fue usado
        verify(mockObserver).update(anyString(), anyString(), anyString());
        // El dummy existe pero no interfiere
        assertNotNull(dummyObserver);
    }

    // ==================== FAKE ====================
    // Fake: Implementación funcional simplificada
    
    static class FakeObserver implements OrderObserver {
        List<String> notifications = new ArrayList<>();
        
        @Override
        public void update(String orderId, String status, String message) {
            notifications.add(orderId + ":" + status);
        }

        @Override
        public String getObserverName() {
            return "Fake Observer";
        }
    }

    @Test
    @DisplayName("FAKE: Observer con implementación simplificada")
    void testFakeObserver() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-002");
        FakeObserver fakeObserver = new FakeObserver();

        // Act
        tracker.attach(fakeObserver);
        tracker.updateStatus("CONFIRMADA", "Orden confirmada");
        tracker.updateStatus("EN_PREPARACION", "Preparando orden");
        tracker.updateStatus("LISTA", "Orden lista");

        // Assert
        assertEquals(3, fakeObserver.notifications.size());
        assertThat(fakeObserver.notifications).contains("ORD-002:CONFIRMADA");
        assertThat(fakeObserver.notifications).contains("ORD-002:EN_PREPARACION");
        assertThat(fakeObserver.notifications).contains("ORD-002:LISTA");
    }

    // ==================== STUB ====================
    // Stub: Proporciona respuestas predefinidas
    
    @Test
    @DisplayName("STUB: Observer con respuestas preprogramadas")
    void testStubObserver() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-003");
        
        // Stub usando Mockito
        OrderObserver stubObserver = mock(OrderObserver.class);
        when(stubObserver.getObserverName()).thenReturn("Stub Observer");
        // No hace nada cuando se llama update (comportamiento por defecto)

        // Act
        tracker.attach(stubObserver);
        tracker.updateStatus("CONFIRMADA", "Test");

        // Assert
        verify(stubObserver).update("ORD-003", "CONFIRMADA", "Test");
        assertEquals("Stub Observer", stubObserver.getObserverName());
    }

    // ==================== SPY ====================
    // Spy: Objeto real parcialmente mockeado
    
    @Test
    @DisplayName("SPY: Observer real con verificación de llamadas")
    void testSpyObserver() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-004");

        // Act
        tracker.attach(spyNotifier);
        tracker.updateStatus("CONFIRMADA", "Pago recibido");

        // Assert - Spy permite verificar llamadas en objeto real
        verify(spyNotifier).update(eq("ORD-004"), eq("CONFIRMADA"), anyString());
        verify(spyNotifier).getObserverName();
        
        // También podemos verificar el comportamiento real
        assertEquals("Cliente: Test Customer", spyNotifier.getObserverName());
    }

    @Test
    @DisplayName("SPY: Modificar comportamiento parcial")
    void testSpyWithPartialMocking() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-005");
        
        // Modificamos solo getObserverName, el resto funciona normal
        doReturn("Modified Spy Name").when(spyNotifier).getObserverName();

        // Act
        tracker.attach(spyNotifier);
        tracker.updateStatus("LISTA", "Orden completada");

        // Assert
        assertEquals("Modified Spy Name", spyNotifier.getObserverName());
        verify(spyNotifier).update("ORD-005", "LISTA", "Orden completada");
    }

    // ==================== MOCK ====================
    // Mock: Verifica comportamiento y interacciones
    
    @Test
    @DisplayName("MOCK: Verificación completa de interacciones")
    void testMockObserver() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-006");
        when(mockObserver.getObserverName()).thenReturn("Mock Observer");

        // Act
        tracker.attach(mockObserver);
        tracker.updateStatus("CONFIRMADA", "Test message");

        // Assert - Verificación estricta
        verify(mockObserver, times(1)).update("ORD-006", "CONFIRMADA", "Test message");
        verify(mockObserver, times(1)).getObserverName();
        verifyNoMoreInteractions(mockObserver);
    }

    @Test
    @DisplayName("MOCK: Capturar argumentos")
    void testMockWithArgumentCaptor() {
        // Arrange
        OrderTracker tracker = new OrderTracker("ORD-007");

        // Act
        tracker.attach(mockObserver);
        tracker.updateStatus("CONFIRMADA", "Primer mensaje");
        tracker.updateStatus("EN_PREPARACION", "Segundo mensaje");

        // Assert - Capturar todos los mensajes
        verify(mockObserver, times(2)).update(anyString(), anyString(), messageCaptor.capture());
        
        List<String> capturedMessages = messageCaptor.getAllValues();
        assertEquals(2, capturedMessages.size());
        assertThat(capturedMessages).contains("Primer mensaje", "Segundo mensaje");
    }

    // ==================== COMPARACIÓN DE DOUBLES ====================
    
    @Nested
    @DisplayName("Comparación de Test Doubles")
    class TestDoublesComparison {

        @Test
        @DisplayName("Todos los doubles en acción")
        void testAllDoublesInAction() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-ALL");
            
            // Dummy - Solo existe, no se usa
            OrderObserver dummy = mock(OrderObserver.class);
            
            // Fake - Implementación simple
            FakeObserver fake = new FakeObserver();
            
            // Stub - Respuestas predefinidas
            OrderObserver stub = mock(OrderObserver.class);
            when(stub.getObserverName()).thenReturn("Stub");
            
            // Mock - Verificación completa
            OrderObserver mockObs = mock(OrderObserver.class);

            // Act
            tracker.attach(fake);  // Solo agregamos fake y mock
            tracker.attach(mockObs);
            tracker.updateStatus("CONFIRMADA", "Test con todos los doubles");

            // Assert
            // Fake guardó la notificación
            assertEquals(1, fake.notifications.size());
            
            // Mock fue llamado
            verify(mockObs).update(eq("ORD-ALL"), eq("CONFIRMADA"), anyString());
            
            // Dummy y Stub no fueron agregados, entonces no reciben notificaciones
            verify(dummy, never()).update(anyString(), anyString(), anyString());
            verify(stub, never()).update(anyString(), anyString(), anyString());
        }
    }

    @Nested
    @DisplayName("Tests de detach y múltiples observers")
    class MultipleObserversTests {

        @Test
        @DisplayName("Debe notificar múltiples observers")
        void testMultipleObservers() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-MULTI");
            OrderObserver observer1 = mock(OrderObserver.class);
            OrderObserver observer2 = mock(OrderObserver.class);
            OrderObserver observer3 = mock(OrderObserver.class);

            // Act
            tracker.attach(observer1);
            tracker.attach(observer2);
            tracker.attach(observer3);
            tracker.updateStatus("CONFIRMADA", "Mensaje múltiple");

            // Assert
            verify(observer1).update(anyString(), anyString(), anyString());
            verify(observer2).update(anyString(), anyString(), anyString());
            verify(observer3).update(anyString(), anyString(), anyString());
        }

        @Test
        @DisplayName("Debe dejar de notificar después de detach")
        void testDetachObserver() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-DETACH");
            OrderObserver observer = mock(OrderObserver.class);

            // Act & Assert
            tracker.attach(observer);
            tracker.updateStatus("ESTADO1", "Mensaje 1");
            verify(observer, times(1)).update(anyString(), anyString(), anyString());

            tracker.detach(observer);
            tracker.updateStatus("ESTADO2", "Mensaje 2");
            // Sigue siendo 1 vez, no se llamó de nuevo
            verify(observer, times(1)).update(anyString(), anyString(), anyString());
        }
    }
}
