package com.example.patterns.behavioral.observer;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests adicionales para mejorar cobertura del patrón Observer
 * Cubre: AnalyticsSystem, KitchenDisplay, y casos adicionales de OrderTracker
 */
@DisplayName("Observer Pattern - Enhanced Coverage Tests")
class ObserverPatternEnhancedTest {

    @Nested
    @DisplayName("AnalyticsSystem Tests")
    class AnalyticsSystemTests {

        @Test
        @DisplayName("Debe rastrear órdenes correctamente")
        void shouldTrackOrders() {
            // Arrange
            AnalyticsSystem analytics = new AnalyticsSystem();
            
            // Act & Assert - Inicial
            assertThat(analytics.getOrdersTracked()).isEqualTo(0);
            
            // Act - Primera orden
            analytics.update("ORD-001", "RECIBIDA", "Nueva orden");
            assertThat(analytics.getOrdersTracked()).isEqualTo(1);
            
            // Act - Segunda orden
            analytics.update("ORD-002", "CONFIRMADA", "Orden confirmada");
            assertThat(analytics.getOrdersTracked()).isEqualTo(2);
            
            // Act - Tercera orden
            analytics.update("ORD-003", "EN_PREPARACION", "Orden en cocina");
            assertThat(analytics.getOrdersTracked()).isEqualTo(3);
        }

        @Test
        @DisplayName("Debe tener nombre de observador correcto")
        void shouldHaveCorrectObserverName() {
            // Arrange
            AnalyticsSystem analytics = new AnalyticsSystem();
            
            // Act
            String name = analytics.getObserverName();
            
            // Assert
            assertThat(name).isEqualTo("Sistema de Analíticas");
        }

        @Test
        @DisplayName("Debe incrementar contador con múltiples actualizaciones")
        void shouldIncrementCounterWithMultipleUpdates() {
            // Arrange
            AnalyticsSystem analytics = new AnalyticsSystem();
            
            // Act - 10 órdenes
            for (int i = 1; i <= 10; i++) {
                analytics.update("ORD-" + i, "STATUS-" + i, "Message " + i);
            }
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(10);
        }

        @Test
        @DisplayName("Debe procesar actualizaciones con diferentes estados")
        void shouldProcessUpdatesWithDifferentStatuses() {
            // Arrange
            AnalyticsSystem analytics = new AnalyticsSystem();
            String[] statuses = {"RECIBIDA", "CONFIRMADA", "EN_PREPARACION", "LISTA", "ENTREGADA"};
            
            // Act
            for (int i = 0; i < statuses.length; i++) {
                analytics.update("ORD-" + (i + 1), statuses[i], "Estado: " + statuses[i]);
            }
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(5);
        }

        @Test
        @DisplayName("Debe manejar órdenes con IDs largos")
        void shouldHandleOrdersWithLongIds() {
            // Arrange
            AnalyticsSystem analytics = new AnalyticsSystem();
            String longOrderId = "ORD-2024-11-13-PREMIUM-VIP-001-SPECIAL";
            
            // Act
            analytics.update(longOrderId, "RECIBIDA", "Orden premium");
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("KitchenDisplay Tests")
    class KitchenDisplayTests {

        @Test
        @DisplayName("Debe crear display con número específico")
        void shouldCreateDisplayWithSpecificNumber() {
            // Arrange & Act
            KitchenDisplay display1 = new KitchenDisplay(1);
            KitchenDisplay display2 = new KitchenDisplay(5);
            KitchenDisplay display3 = new KitchenDisplay(10);
            
            // Assert
            assertThat(display1.getObserverName()).isEqualTo("Pantalla Cocina #1");
            assertThat(display2.getObserverName()).isEqualTo("Pantalla Cocina #5");
            assertThat(display3.getObserverName()).isEqualTo("Pantalla Cocina #10");
        }

        @Test
        @DisplayName("Debe procesar actualización de orden CONFIRMADA")
        void shouldProcessConfirmedOrderUpdate() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(1);
            
            // Act & Assert - No debe lanzar excepciones
            assertDoesNotThrow(() -> {
                display.update("ORD-001", "CONFIRMADA", "Pago confirmado");
            });
        }

        @Test
        @DisplayName("Debe procesar actualización de orden RECIBIDA")
        void shouldProcessReceivedOrderUpdate() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(2);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                display.update("ORD-002", "RECIBIDA", "Nueva orden recibida");
            });
        }

        @Test
        @DisplayName("Debe procesar actualización de orden EN_PREPARACION")
        void shouldProcessInPreparationOrderUpdate() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(3);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                display.update("ORD-003", "EN_PREPARACION", "Orden en cocina");
            });
        }

        @Test
        @DisplayName("Debe manejar múltiples actualizaciones en la misma pantalla")
        void shouldHandleMultipleUpdatesOnSameDisplay() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(1);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                display.update("ORD-001", "RECIBIDA", "Orden 1");
                display.update("ORD-002", "CONFIRMADA", "Orden 2");
                display.update("ORD-003", "EN_PREPARACION", "Orden 3");
                display.update("ORD-004", "LISTA", "Orden 4");
            });
        }

        @Test
        @DisplayName("Debe funcionar con diferentes números de display")
        void shouldWorkWithDifferentDisplayNumbers() {
            // Arrange
            KitchenDisplay display1 = new KitchenDisplay(1);
            KitchenDisplay display2 = new KitchenDisplay(2);
            KitchenDisplay display3 = new KitchenDisplay(3);
            
            // Act
            display1.update("ORD-A", "RECIBIDA", "Display 1");
            display2.update("ORD-B", "CONFIRMADA", "Display 2");
            display3.update("ORD-C", "EN_PREPARACION", "Display 3");
            
            // Assert
            assertThat(display1.getObserverName()).contains("1");
            assertThat(display2.getObserverName()).contains("2");
            assertThat(display3.getObserverName()).contains("3");
        }

        @Test
        @DisplayName("Debe manejar estado LISTA")
        void shouldHandleListaStatus() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(4);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                display.update("ORD-005", "LISTA", "Orden lista para servir");
            });
        }

        @Test
        @DisplayName("Debe manejar estado ENTREGADA")
        void shouldHandleEntregadaStatus() {
            // Arrange
            KitchenDisplay display = new KitchenDisplay(5);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                display.update("ORD-006", "ENTREGADA", "Orden entregada al cliente");
            });
        }
    }

    @Nested
    @DisplayName("OrderTracker Additional Tests")
    class OrderTrackerAdditionalTests {

        @Test
        @DisplayName("Debe manejar estado personalizado")
        void shouldHandleCustomStatus() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-CUSTOM-001");
            CustomerNotifier notifier = new CustomerNotifier("Cliente Test");
            tracker.attach(notifier);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                tracker.updateStatus("ESTADO_PERSONALIZADO", "Estado especial");
            });
        }

        @Test
        @DisplayName("Debe manejar múltiples cambios de estado")
        void shouldHandleMultipleStatusChanges() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-MULTI-001");
            AnalyticsSystem analytics = new AnalyticsSystem();
            tracker.attach(analytics);
            
            // Act
            tracker.updateStatus("RECIBIDA", "Orden recibida");
            tracker.updateStatus("CONFIRMADA", "Pago confirmado");
            tracker.updateStatus("EN_PREPARACION", "En cocina");
            tracker.updateStatus("LISTA", "Lista para servir");
            tracker.updateStatus("ENTREGADA", "Entregada al cliente");
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(5);
            assertThat(tracker.getCurrentStatus()).isEqualTo("ENTREGADA");
        }

        @Test
        @DisplayName("Debe retornar orderId correcto")
        void shouldReturnCorrectOrderId() {
            // Arrange & Act
            OrderTracker tracker1 = new OrderTracker("ORD-001");
            OrderTracker tracker2 = new OrderTracker("ORD-002");
            
            // Assert
            assertThat(tracker1.getOrderId()).isEqualTo("ORD-001");
            assertThat(tracker2.getOrderId()).isEqualTo("ORD-002");
        }

        @Test
        @DisplayName("Debe tener estado inicial CREADA")
        void shouldHaveInitialStatusCreada() {
            // Arrange & Act
            OrderTracker tracker = new OrderTracker("ORD-NEW");
            
            // Assert
            assertThat(tracker.getCurrentStatus()).isEqualTo("CREADA");
        }
    }

    @Nested
    @DisplayName("Integration Tests - Multiple Observers")
    class IntegrationMultipleObserversTests {

        @Test
        @DisplayName("Debe notificar a múltiples tipos de observadores")
        void shouldNotifyMultipleObserverTypes() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-MULTI-001");
            CustomerNotifier customer = new CustomerNotifier("Cliente VIP");
            AnalyticsSystem analytics = new AnalyticsSystem();
            KitchenDisplay display = new KitchenDisplay(1);
            
            tracker.attach(customer);
            tracker.attach(analytics);
            tracker.attach(display);
            
            // Act
            tracker.updateStatus("RECIBIDA", "Orden recibida");
            tracker.updateStatus("CONFIRMADA", "Pago confirmado");
            tracker.updateStatus("EN_PREPARACION", "En cocina");
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(3);
        }

        @Test
        @DisplayName("Debe manejar escenario con múltiples displays y analytics")
        void shouldHandleMultipleDisplaysAndAnalytics() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-RESTAURANT");
            AnalyticsSystem analytics = new AnalyticsSystem();
            KitchenDisplay display1 = new KitchenDisplay(1);
            KitchenDisplay display2 = new KitchenDisplay(2);
            KitchenDisplay display3 = new KitchenDisplay(3);
            
            tracker.attach(analytics);
            tracker.attach(display1);
            tracker.attach(display2);
            tracker.attach(display3);
            
            // Act - 5 cambios de estado
            tracker.updateStatus("RECIBIDA", "Orden recibida");
            tracker.updateStatus("CONFIRMADA", "Pago confirmado");
            tracker.updateStatus("EN_PREPARACION", "En cocina");
            tracker.updateStatus("LISTA", "Lista");
            tracker.updateStatus("ENTREGADA", "Entregada");
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(5);
        }

        @Test
        @DisplayName("Debe permitir remover analytics system")
        void shouldAllowDetachAnalyticsSystem() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-DETACH-001");
            AnalyticsSystem analytics = new AnalyticsSystem();
            
            tracker.attach(analytics);
            tracker.updateStatus("RECIBIDA", "Primera actualización");
            
            // Act
            tracker.detach(analytics);
            tracker.updateStatus("CONFIRMADA", "Segunda actualización");
            
            // Assert - Solo debe contar la primera
            assertThat(analytics.getOrdersTracked()).isEqualTo(1);
        }

        @Test
        @DisplayName("Debe permitir remover kitchen display")
        void shouldAllowDetachKitchenDisplay() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-DETACH-002");
            KitchenDisplay display = new KitchenDisplay(1);
            
            tracker.attach(display);
            
            // Act & Assert
            assertDoesNotThrow(() -> {
                tracker.updateStatus("CONFIRMADA", "Antes de detach");
                tracker.detach(display);
                tracker.updateStatus("EN_PREPARACION", "Después de detach");
            });
        }

        @Test
        @DisplayName("Debe manejar attach y detach de múltiples observadores")
        void shouldHandleMultipleAttachDetach() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-COMPLEX");
            AnalyticsSystem analytics = new AnalyticsSystem();
            KitchenDisplay display1 = new KitchenDisplay(1);
            KitchenDisplay display2 = new KitchenDisplay(2);
            
            // Act
            tracker.attach(analytics);
            tracker.attach(display1);
            tracker.updateStatus("RECIBIDA", "Con 2 observers");
            
            tracker.attach(display2);
            tracker.updateStatus("CONFIRMADA", "Con 3 observers");
            
            tracker.detach(display1);
            tracker.updateStatus("EN_PREPARACION", "Con 2 observers de nuevo");
            
            // Assert
            assertThat(analytics.getOrdersTracked()).isEqualTo(3);
        }
    }
}
