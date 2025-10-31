package com.example.e2e;

import com.example.patterns.creational.singleton.RestaurantConfig;
import com.example.patterns.creational.builder.Menu;
import com.example.patterns.creational.builder.MenuDirector;
import com.example.patterns.creational.factorymethod.*;
import com.example.patterns.behavioral.observer.*;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * End-to-End Tests - Flujos completos del sistema de restaurante
 * Simula escenarios reales de principio a fin
 */
@DisplayName("E2E Tests - Sistema Completo de Restaurante")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestaurantE2ETest {

    private static RestaurantConfig config;
    private static MenuDirector menuDirector;

    @BeforeAll
    static void setup() {
        config = RestaurantConfig.getInstance();
        menuDirector = new MenuDirector();
    }

    @Nested
    @DisplayName("Escenario 1: Orden Completa de Cliente")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ScenarioCompleteCustomerOrder {

        private Menu customerMenu;
        private String orderId;
        private OrderTracker tracker;

        @Test
        @Order(1)
        @DisplayName("Paso 1: Cliente solicita menú del día")
        void step1_CustomerRequestsMenu() {
            // Act
            customerMenu = menuDirector.createFamilyMenu();

            // Assert
            assertNotNull(customerMenu);
            assertEquals("Menú Familiar", customerMenu.getName());
            assertTrue(customerMenu.getTotalPrice() > 0);
        }

        @Test
        @Order(2)
        @DisplayName("Paso 2: Sistema genera número de orden")
        void step2_SystemGeneratesOrderNumber() {
            // Act
            int orderNum = config.getNextOrderNumber();
            orderId = String.format("ORD-%04d", orderNum);

            // Assert
            assertNotNull(orderId);
            assertTrue(orderId.startsWith("ORD-"));
        }

        @Test
        @Order(3)
        @DisplayName("Paso 3: Sistema calcula total con impuestos")
        void step3_CalculateTotalWithTax() {
            // Arrange
            Menu menu = menuDirector.createFamilyMenu();

            // Act
            double subtotal = menu.getTotalPrice();
            double tax = config.calculateTax(subtotal);
            double total = config.calculateTotal(subtotal);

            // Assert
            assertTrue(tax > 0);
            assertEquals(subtotal + tax, total, 0.01);
        }

        @Test
        @Order(4)
        @DisplayName("Paso 4: Sistema inicia tracking de orden")
        void step4_InitiateOrderTracking() {
            // Act
            tracker = new OrderTracker("ORD-0001");
            tracker.updateStatus("RECIBIDA", "Orden recibida del cliente");

            // Assert
            assertEquals("RECIBIDA", tracker.getCurrentStatus());
        }

        @Test
        @Order(5)
        @DisplayName("Paso 5: Cliente confirma y paga")
        void step5_CustomerConfirmsAndPays() {
            // Arrange
            OrderTracker orderTracker = new OrderTracker("ORD-0002");
            Menu menu = menuDirector.createVegetarianMenu();
            double total = config.calculateTotal(menu.getTotalPrice());

            // Act
            orderTracker.updateStatus("CONFIRMADA", "Pago de $" + String.format("%.2f", total) + " confirmado");

            // Assert
            assertEquals("CONFIRMADA", orderTracker.getCurrentStatus());
        }

        @Test
        @Order(6)
        @DisplayName("Paso 6: Cocina prepara la orden")
        void step6_KitchenPreparesOrder() {
            // Arrange
            OrderTracker orderTracker = new OrderTracker("ORD-0003");

            // Act
            orderTracker.updateStatus("EN_PREPARACION", "La cocina está preparando su orden");

            // Assert
            assertEquals("EN_PREPARACION", orderTracker.getCurrentStatus());
        }

        @Test
        @Order(7)
        @DisplayName("Paso 7: Orden lista y entregada")
        void step7_OrderReadyAndDelivered() {
            // Arrange
            OrderTracker orderTracker = new OrderTracker("ORD-0004");

            // Act
            orderTracker.updateStatus("LISTA", "Orden lista para servir");
            orderTracker.updateStatus("ENTREGADA", "Orden entregada al cliente");

            // Assert
            assertEquals("ENTREGADA", orderTracker.getCurrentStatus());
        }
    }

    @Nested
    @DisplayName("Escenario 2: Día Completo del Restaurante")
    class ScenarioCompleteRestaurantDay {

        @Test
        @DisplayName("Simular día completo con múltiples órdenes")
        void testCompleteDay() {
            // APERTURA
            assertNotNull(config.getRestaurantName());
            assertNotNull(config.getOpeningTime());

            // DESAYUNOS (3 órdenes)
            Menu breakfast1 = new Menu.Builder()
                .name("Desayuno 1")
                .addMainCourse("Huevos", 8.00)
                .addBeverage("Café", 3.00)
                .build();

            Menu breakfast2 = new Menu.Builder()
                .name("Desayuno 2")
                .addMainCourse("Pancakes", 9.50)
                .addBeverage("Jugo", 4.00)
                .build();

            double breakfastRevenue = breakfast1.getTotalPrice() + breakfast2.getTotalPrice();

            // ALMUERZOS (4 órdenes)
            Menu lunch1 = menuDirector.createFamilyMenu();
            Menu lunch2 = menuDirector.createHealthyMenu();
            Menu lunch3 = menuDirector.createVegetarianMenu();

            double lunchRevenue = lunch1.getTotalPrice() + 
                                 lunch2.getTotalPrice() + 
                                 lunch3.getTotalPrice();

            // CENAS (3 órdenes)
            Menu dinner1 = menuDirector.createGourmetMenu();
            Menu dinner2 = menuDirector.createFamilyMenu();

            double dinnerRevenue = dinner1.getTotalPrice() + dinner2.getTotalPrice();

            // CIERRE - Cálculos finales
            double totalRevenue = breakfastRevenue + lunchRevenue + dinnerRevenue;
            double totalTax = config.calculateTax(totalRevenue);
            double finalRevenue = config.calculateTotal(totalRevenue);

            // Assert
            assertAll("Complete day operations",
                () -> assertTrue(breakfastRevenue > 0, "Breakfast revenue"),
                () -> assertTrue(lunchRevenue > 0, "Lunch revenue"),
                () -> assertTrue(dinnerRevenue > 0, "Dinner revenue"),
                () -> assertTrue(totalRevenue > 0, "Total revenue"),
                () -> assertTrue(finalRevenue > totalRevenue, "Final with tax"),
                () -> assertNotNull(config.getClosingTime())
            );
        }
    }

    @Nested
    @DisplayName("Escenario 3: Múltiples Tipos de Restaurantes")
    class ScenarioMultipleRestaurantTypes {

        @Test
        @DisplayName("Procesar órdenes de diferentes especialidades")
        void testMultipleRestaurantSpecialties() {
            // Arrange - Factory Method Pattern
            Restaurant italian = new ItalianRestaurant();
            Restaurant pasta = new PastaRestaurant();
            Restaurant healthy = new HealthyRestaurant();

            // Capturar output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Act - Procesar órdenes
            italian.orderDish();
            pasta.orderDish();
            healthy.orderDish();

            System.setOut(originalOut);
            String output = outputStream.toString();

            // Assert
            assertAll("Multiple restaurant types",
                () -> assertTrue(output.contains("Pizza Margherita") || 
                                output.contains("Pasta Carbonara") || 
                                output.contains("Ensalada César")),
                () -> assertTrue(output.length() > 0)
            );
        }

        @Test
        @DisplayName("Crear platillos de cada tipo de restaurante")
        void testCreateDishesFromEachType() {
            // Act
            Dish pizzaDish = new ItalianRestaurant().createDish();
            Dish pastaDish = new PastaRestaurant().createDish();
            Dish saladDish = new HealthyRestaurant().createDish();

            // Assert
            assertAll("Dish creation from different restaurants",
                () -> assertInstanceOf(Pizza.class, pizzaDish),
                () -> assertInstanceOf(Pasta.class, pastaDish),
                () -> assertInstanceOf(Salad.class, saladDish),
                () -> assertEquals("Pizza Margherita", pizzaDish.getName()),
                () -> assertEquals("Pasta Carbonara", pastaDish.getName()),
                () -> assertEquals("Ensalada César", saladDish.getName())
            );
        }
    }

    @Nested
    @DisplayName("Escenario 4: Tracking de Múltiples Órdenes")
    class ScenarioMultipleOrderTracking {

        @Test
        @DisplayName("Rastrear 10 órdenes simultáneas")
        void testTrack10Orders() {
            // Arrange
            int orderCount = 10;
            OrderTracker[] trackers = new OrderTracker[orderCount];
            CustomerNotifier[] customers = new CustomerNotifier[orderCount];

            // Act - Crear y rastrear órdenes
            for (int i = 0; i < orderCount; i++) {
                String orderId = "ORD-" + String.format("%03d", i + 1);
                trackers[i] = new OrderTracker(orderId);
                customers[i] = new CustomerNotifier("Cliente " + (i + 1));
                trackers[i].attach(customers[i]);
                
                trackers[i].updateStatus("RECIBIDA", "Orden recibida");
                trackers[i].updateStatus("CONFIRMADA", "Pago confirmado");
                trackers[i].updateStatus("EN_PREPARACION", "En cocina");
            }

            // Assert - Todas deben estar en preparación
            for (int i = 0; i < orderCount; i++) {
                assertEquals("EN_PREPARACION", trackers[i].getCurrentStatus());
            }
        }

        @Test
        @DisplayName("Completar órdenes en diferentes tiempos")
        void testCompleteOrdersAtDifferentTimes() {
            // Arrange
            OrderTracker quick = new OrderTracker("ORD-QUICK");
            OrderTracker medium = new OrderTracker("ORD-MEDIUM");
            OrderTracker slow = new OrderTracker("ORD-SLOW");

            // Act - Simular diferentes tiempos de preparación
            quick.updateStatus("RECIBIDA", "Orden rápida");
            quick.updateStatus("CONFIRMADA", "Pago");
            quick.updateStatus("EN_PREPARACION", "Preparando");
            quick.updateStatus("LISTA", "Lista");
            quick.updateStatus("ENTREGADA", "Entregada");

            medium.updateStatus("RECIBIDA", "Orden media");
            medium.updateStatus("CONFIRMADA", "Pago");
            medium.updateStatus("EN_PREPARACION", "Preparando");
            medium.updateStatus("LISTA", "Lista");

            slow.updateStatus("RECIBIDA", "Orden lenta");
            slow.updateStatus("CONFIRMADA", "Pago");
            slow.updateStatus("EN_PREPARACION", "Preparando");

            // Assert
            assertEquals("ENTREGADA", quick.getCurrentStatus());
            assertEquals("LISTA", medium.getCurrentStatus());
            assertEquals("EN_PREPARACION", slow.getCurrentStatus());
        }
    }

    @Nested
    @DisplayName("Escenario 5: Manejo de Errores y Cancelaciones")
    class ScenarioErrorHandlingAndCancellations {

        @Test
        @DisplayName("Cliente cancela orden antes de preparación")
        void testCancelOrderBeforePreparation() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-CANCEL-001");

            // Act
            tracker.updateStatus("RECIBIDA", "Orden recibida");
            tracker.updateStatus("CANCELADA", "Cliente canceló la orden");

            // Assert
            assertEquals("CANCELADA", tracker.getCurrentStatus());
        }

        @Test
        @DisplayName("Manejar orden sin items")
        void testHandleEmptyOrder() {
            // Act
            Menu emptyMenu = new Menu.Builder()
                .name("Orden Vacía")
                .build();

            // Assert
            assertEquals(0.0, emptyMenu.getTotalPrice());
            assertTrue(emptyMenu.getAppetizers().isEmpty());
            assertTrue(emptyMenu.getMainCourses().isEmpty());
        }
    }

    @Nested
    @DisplayName("Escenario 6: Performance y Escalabilidad")
    class ScenarioPerformanceAndScalability {

        @Test
        @DisplayName("Generar 100 números de orden únicos")
        void testGenerate100UniqueOrderNumbers() {
            // Arrange
            java.util.Set<Integer> orderNumbers = new java.util.HashSet<>();

            // Act
            for (int i = 0; i < 100; i++) {
                int orderNum = config.getNextOrderNumber();
                orderNumbers.add(orderNum);
            }

            // Assert
            assertEquals(100, orderNumbers.size(), "All order numbers should be unique");
        }

        @Test
        @DisplayName("Crear 50 menús simultáneamente")
        void testCreate50MenusSimultaneously() {
            // Act
            java.util.List<Menu> menus = new java.util.ArrayList<>();
            for (int i = 0; i < 50; i++) {
                Menu menu = new Menu.Builder()
                    .name("Menú " + i)
                    .addMainCourse("Plato " + i, 10.00 + i)
                    .build();
                menus.add(menu);
            }

            // Assert
            assertEquals(50, menus.size());
            assertTrue(menus.stream().allMatch(m -> m.getTotalPrice() > 0));
        }
    }

    @Nested
    @DisplayName("Escenario 7: Integración Total")
    class ScenarioTotalIntegration {

        @Test
        @DisplayName("Flujo completo usando todos los patrones implementados")
        void testCompleteFlowWithAllPatterns() {
            // 1. SINGLETON - Configuración
            RestaurantConfig cfg = RestaurantConfig.getInstance();
            assertNotNull(cfg);

            // 2. FACTORY METHOD - Crear platillo
            Restaurant restaurant = new ItalianRestaurant();
            Dish dish = restaurant.createDish();
            assertNotNull(dish);

            // 3. BUILDER - Crear menú
            Menu menu = new Menu.Builder()
                .name("Menú Integración Total")
                .addMainCourse(dish.getName(), dish.getPrice())
                .addBeverage("Bebida", 5.00)
                .build();
            assertNotNull(menu);

            // 4. SINGLETON - Cálculos
            double total = cfg.calculateTotal(menu.getTotalPrice());
            assertTrue(total > 0);

            // 5. OBSERVER - Tracking
            String orderId = "ORD-" + cfg.getNextOrderNumber();
            OrderTracker tracker = new OrderTracker(orderId);
            CustomerNotifier customer = new CustomerNotifier("Cliente Final");
            tracker.attach(customer);
            
            tracker.updateStatus("RECIBIDA", "Orden completa recibida");
            tracker.updateStatus("CONFIRMADA", "Pago de $" + total);
            tracker.updateStatus("EN_PREPARACION", "Cocina preparando");
            tracker.updateStatus("LISTA", "Orden lista");
            tracker.updateStatus("ENTREGADA", "Orden entregada exitosamente");

            // Assert final
            assertEquals("ENTREGADA", tracker.getCurrentStatus());
        }
    }
}
