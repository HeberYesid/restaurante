package com.example.integration;

import com.example.patterns.creational.singleton.RestaurantConfig;
import com.example.patterns.creational.builder.Menu;
import com.example.patterns.creational.builder.MenuDirector;
import com.example.patterns.creational.factorymethod.*;
import com.example.patterns.behavioral.observer.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Integration Tests - Pruebas de integración correctas
 * Combinan múltiples patrones sin errores de compilación
 */
@DisplayName("Integration Tests - Combinación de Patrones")
class IntegrationTest {

    private static RestaurantConfig config;
    private static MenuDirector menuDirector;

    @BeforeAll
    static void globalSetup() {
        config = RestaurantConfig.getInstance();
        menuDirector = new MenuDirector();
    }

    @Nested
    @DisplayName("Singleton + Builder Integration")
    class SingletonBuilderIntegration {

        @Test
        @DisplayName("Configuración debe persistir entre creaciones de menús")
        void testConfigPersistence() {
            // Arrange
            RestaurantConfig config1 = RestaurantConfig.getInstance();
            config1.setTaxRate(0.15);

            // Act - Crear menú y calcular con tax
            Menu menu = new Menu.Builder()
                .name("Menú Test")
                .addMainCourse("Plato", 100.00)
                .build();

            RestaurantConfig config2 = RestaurantConfig.getInstance();
            double total = config2.calculateTotal(menu.getTotalPrice());

            // Assert
            assertSame(config1, config2);
            assertEquals(115.00, total, 0.01);
        }

        @Test
        @DisplayName("Director debe usar configuración singleton")
        void testDirectorWithSingleton() {
            // Act
            Menu familyMenu = menuDirector.createFamilyMenu();
            double totalWithTax = config.calculateTotal(familyMenu.getTotalPrice());

            // Assert
            assertTrue(totalWithTax > familyMenu.getTotalPrice());
            assertNotNull(config.getRestaurantName());
        }
    }

    @Nested
    @DisplayName("Factory Method + Observer Integration")
    class FactoryObserverIntegration {

        @Test
        @DisplayName("Crear platillo y rastrear con observer")
        void testDishCreationWithTracking() {
            // Arrange - Factory Method
            Restaurant restaurant = new ItalianRestaurant();
            Dish dish = restaurant.createDish();

            // Observer - Tracking
            String orderId = "ORD-" + config.getNextOrderNumber();
            OrderTracker tracker = new OrderTracker(orderId);
            CustomerNotifier customer = new CustomerNotifier("Cliente Test");

            // Act
            tracker.attach(customer);
            tracker.updateStatus("RECIBIDA", "Orden de " + dish.getName());
            tracker.updateStatus("EN_PREPARACION", "Preparando " + dish.getName());

            // Assert
            assertAll(
                () -> assertNotNull(dish),
                () -> assertEquals("EN_PREPARACION", tracker.getCurrentStatus()),
                () -> assertTrue(dish.getPrice() > 0)
            );
        }

        @Test
        @DisplayName("Múltiples restaurantes con tracking")
        void testMultipleRestaurantsWithTracking() {
            // Arrange
            Restaurant italian = new ItalianRestaurant();
            Restaurant pasta = new PastaRestaurant();
            Restaurant healthy = new HealthyRestaurant();

            // Act - Crear órdenes
            Dish dish1 = italian.createDish();
            OrderTracker tracker1 = new OrderTracker("ORD-001");
            tracker1.updateStatus("CONFIRMADA", dish1.getName());

            Dish dish2 = pasta.createDish();
            OrderTracker tracker2 = new OrderTracker("ORD-002");
            tracker2.updateStatus("CONFIRMADA", dish2.getName());

            Dish dish3 = healthy.createDish();
            OrderTracker tracker3 = new OrderTracker("ORD-003");
            tracker3.updateStatus("CONFIRMADA", dish3.getName());

            // Assert
            assertAll(
                () -> assertEquals("CONFIRMADA", tracker1.getCurrentStatus()),
                () -> assertEquals("CONFIRMADA", tracker2.getCurrentStatus()),
                () -> assertEquals("CONFIRMADA", tracker3.getCurrentStatus())
            );
        }
    }

    @Nested
    @DisplayName("Builder + Singleton + Tax Calculation")
    class BuilderSingletonTaxIntegration {

        @Test
        @DisplayName("Crear menú y calcular total con impuestos")
        void testMenuWithTaxCalculation() {
            // Arrange - Builder
            Menu menu = new Menu.Builder()
                .name("Menú Completo")
                .addAppetizer("Ensalada", 8.50)
                .addMainCourse("Filete", 25.00)
                .addDessert("Pastel", 6.50)
                .addBeverage("Vino", 15.00)
                .build();

            // Act - Singleton tax calculation
            double subtotal = menu.getTotalPrice();
            double tax = config.calculateTax(subtotal);
            double total = config.calculateTotal(subtotal);

            // Assert
            assertAll(
                () -> assertEquals(55.00, subtotal, 0.01),
                () -> assertTrue(tax > 0),
                () -> assertEquals(subtotal + tax, total, 0.01),
                () -> assertTrue(total > subtotal)
            );
        }

        @Test
        @DisplayName("Director menus con cálculo de impuestos")
        void testDirectorMenusWithTax() {
            // Act
            Menu family = menuDirector.createFamilyMenu();
            Menu vegetarian = menuDirector.createVegetarianMenu();
            Menu gourmet = menuDirector.createGourmetMenu();
            Menu healthy = menuDirector.createHealthyMenu();

            double totalRevenue = family.getTotalPrice() + 
                                 vegetarian.getTotalPrice() + 
                                 gourmet.getTotalPrice() + 
                                 healthy.getTotalPrice();

            double totalWithTax = config.calculateTotal(totalRevenue);

            // Assert
            assertTrue(totalRevenue > 0);
            assertTrue(totalWithTax > totalRevenue);
        }
    }

    @Nested
    @DisplayName("Observer Pattern with Multiple Orders")
    class MultipleOrdersIntegration {

        @Test
        @DisplayName("Rastrear múltiples órdenes simultáneamente")
        void testMultipleOrderTracking() {
            // Arrange
            OrderTracker[] trackers = new OrderTracker[5];
            for (int i = 0; i < 5; i++) {
                trackers[i] = new OrderTracker("ORD-" + (i + 1));
                trackers[i].updateStatus("RECIBIDA", "Orden " + (i + 1));
            }

            // Act - Actualizar todos
            for (OrderTracker tracker : trackers) {
                tracker.updateStatus("CONFIRMADA", "Pago confirmado");
            }

            // Assert
            for (OrderTracker tracker : trackers) {
                assertEquals("CONFIRMADA", tracker.getCurrentStatus());
            }
        }

        @Test
        @DisplayName("Observer debe poder desconectarse")
        void testObserverDetach() {
            // Arrange
            OrderTracker tracker = new OrderTracker("ORD-DETACH");
            CustomerNotifier customer = new CustomerNotifier("Test");

            // Act
            tracker.attach(customer);
            tracker.updateStatus("ESTADO1", "Mensaje 1");
            
            tracker.detach(customer);
            tracker.updateStatus("ESTADO2", "Mensaje 2");

            // Assert
            assertEquals("ESTADO2", tracker.getCurrentStatus());
        }
    }

    @Nested
    @DisplayName("Singleton Thread Safety")
    class SingletonConcurrencyIntegration {

        @Test
        @DisplayName("Singleton debe ser thread-safe")
        void testSingletonThreadSafety() throws InterruptedException {
            // Arrange
            int threadCount = 10;
            Thread[] threads = new Thread[threadCount];
            RestaurantConfig[] instances = new RestaurantConfig[threadCount];

            // Act
            for (int i = 0; i < threadCount; i++) {
                final int index = i;
                threads[i] = new Thread(() -> {
                    instances[index] = RestaurantConfig.getInstance();
                });
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            // Assert - Todas deben ser la misma instancia
            RestaurantConfig first = instances[0];
            for (int i = 1; i < threadCount; i++) {
                assertSame(first, instances[i]);
            }
        }

        @Test
        @DisplayName("Números de orden deben ser únicos en concurrencia")
        void testOrderNumberConcurrency() throws InterruptedException {
            // Arrange
            int threadCount = 100;
            Thread[] threads = new Thread[threadCount];
            java.util.Set<Integer> orderNumbers = new java.util.concurrent.ConcurrentHashMap<Integer, Boolean>().keySet(Boolean.TRUE);

            // Act
            for (int i = 0; i < threadCount; i++) {
                threads[i] = new Thread(() -> {
                    int orderNum = config.getNextOrderNumber();
                    orderNumbers.add(orderNum);
                });
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

            // Assert - Todos los números deben ser únicos
            assertEquals(threadCount, orderNumbers.size());
        }
    }

    @Nested
    @DisplayName("Complete Order Workflow")
    class CompleteOrderWorkflow {

        @Test
        @DisplayName("Flujo completo: Factory -> Builder -> Singleton -> Observer")
        void testCompleteWorkflow() {
            // Step 1: Factory Method - Crear platillo
            Restaurant restaurant = new ItalianRestaurant();
            Dish dish = restaurant.createDish();
            assertNotNull(dish);

            // Step 2: Builder - Crear menú
            Menu menu = new Menu.Builder()
                .name("Orden Completa")
                .addMainCourse(dish.getName(), dish.getPrice())
                .addBeverage("Agua", 2.00)
                .build();
            assertNotNull(menu);

            // Step 3: Singleton - Calcular total
            double total = config.calculateTotal(menu.getTotalPrice());
            assertTrue(total > menu.getTotalPrice());

            // Step 4: Observer - Rastrear orden
            String orderId = "ORD-" + config.getNextOrderNumber();
            OrderTracker tracker = new OrderTracker(orderId);
            tracker.updateStatus("RECIBIDA", "Orden recibida");
            tracker.updateStatus("CONFIRMADA", "Pago de $" + total);
            tracker.updateStatus("EN_PREPARACION", "Preparando platillos");
            tracker.updateStatus("LISTA", "Orden lista para entregar");

            // Assert
            assertEquals("LISTA", tracker.getCurrentStatus());
        }
    }
}
