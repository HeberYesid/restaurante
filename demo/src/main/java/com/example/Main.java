package com.example;

import com.example.patterns.creational.factorymethod.*;
import com.example.patterns.creational.builder.*;
import com.example.patterns.creational.singleton.*;

import com.example.patterns.structural.decorator.*;
import com.example.patterns.structural.facade.*;

import com.example.patterns.behavioral.chainofresponsibility.*;
import com.example.patterns.behavioral.command.*;
import com.example.patterns.behavioral.observer.*;
import com.example.patterns.behavioral.strategy.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Aplicación principal que demuestra patrones de diseño
 * en el contexto de un sistema de restaurante
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String CREATIONAL = "Creacional";
    private static final String STRUCTURAL = "Estructural";
    private static final String BEHAVIORAL = "Comportamiento";
    private static final String SEPARATOR_60 = "─".repeat(60);
    private static final String SEPARATOR_EQUALS = "=".repeat(60);
    private static final String BACK_OPTION = "0. Volver";
    private static final String SELECT_PATTERN = "Seleccione un patrón: ";
    private static final String HEADER_BOX = "\n\n┌─────────────────────────────────────────────────────────┐";
    private static final String FOOTER_BOX = "└─────────────────────────────────────────────────────────┘";
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("╔═══════════════════════════════════════════════════════╗");
        logger.info("║   SISTEMA DE RESTAURANTE - PATRONES DE DISEÑO       ║");
        logger.info("║   Demostración de Patrones de Diseño GoF            ║");
        logger.info("╚═══════════════════════════════════════════════════════╝\n");

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    showCreationalPatterns();
                    break;
                case 2:
                    showStructuralPatterns();
                    break;
                case 3:
                    showBehavioralPatterns();
                    break;
                case 4:
                    showAllPatterns();
                    break;
                case 0:
                    logger.info("\n¡Gracias por usar el sistema! Hasta pronto.");
                    running = false;
                    break;
                default:
                    logger.info("\nOpción inválida. Intente nuevamente.");
                    break;
            }
        }
        
        scanner.close();
    }

    private static void showMainMenu() {
        logger.info("\n{}", SEPARATOR_EQUALS);
        logger.info("                    MENÚ PRINCIPAL");
        logger.info(SEPARATOR_EQUALS);
        logger.info("1. Patrones Creacionales");
        logger.info("2. Patrones Estructurales");
        logger.info("3. Patrones de Comportamiento");
        logger.info("4. Demostrar TODOS los patrones");
        logger.info("0. Salir");
        logger.info(SEPARATOR_EQUALS);
        logger.info("Seleccione una opción: ");
    }

    private static void showCreationalPatterns() {
        logger.info("\n{}", SEPARATOR_60);
        logger.info("              PATRONES CREACIONALES");
        logger.info(SEPARATOR_60);
        logger.info("1. Factory Method");
        logger.info("2. Builder");
        logger.info("3. Singleton");
        logger.info(BACK_OPTION);
        logger.info(SEPARATOR_60);
        logger.info(SELECT_PATTERN);

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateFactoryMethod();
                break;
            case 2:
                demonstrateBuilder();
                break;
            case 3:
                demonstrateSingleton();
                break;
            default:
                break;
        }
    }

    private static void showStructuralPatterns() {
        logger.info("\n{}", SEPARATOR_60);
        logger.info("              PATRONES ESTRUCTURALES");
        logger.info(SEPARATOR_60);
        logger.info("1. Decorator");
        logger.info("2. Facade");
        logger.info(BACK_OPTION);
        logger.info(SEPARATOR_60);
        logger.info(SELECT_PATTERN);

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateDecorator();
                break;
            case 2:
                demonstrateFacade();
                break;
            default:
                break;
        }
    }

    private static void showBehavioralPatterns() {
        logger.info("\n{}", SEPARATOR_60);
        logger.info("           PATRONES DE COMPORTAMIENTO");
        logger.info(SEPARATOR_60);
        logger.info("1. Chain of Responsibility");
        logger.info("2. Command");
        logger.info("3. Observer");
        logger.info("4. Strategy");
        logger.info(BACK_OPTION);
        logger.info(SEPARATOR_60);
        logger.info(SELECT_PATTERN);

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateChainOfResponsibility();
                break;
            case 2:
                demonstrateCommand();
                break;
            case 3:
                demonstrateObserver();
                break;
            case 4:
                demonstrateStrategy();
                break;
            default:
                break;
        }
    }

    private static void showAllPatterns() {
        logger.info("\n\n");
        logger.info("╔═══════════════════════════════════════════════════════╗");
        logger.info("║      DEMOSTRACIÓN COMPLETA DE TODOS LOS PATRONES      ║");
        logger.info("╚═══════════════════════════════════════════════════════╝");

        // Creacionales
        logger.info(HEADER_BOX);
        logger.info("│               PATRONES CREACIONALES                     │");
        logger.info(FOOTER_BOX);
        demonstrateFactoryMethod();
        pause();
        demonstrateBuilder();
        pause();
        demonstrateSingleton();
        pause();

        // Estructurales
        logger.info(HEADER_BOX);
        logger.info("│               PATRONES ESTRUCTURALES                    │");
        logger.info(FOOTER_BOX);
        demonstrateDecorator();
        pause();
        demonstrateFacade();
        pause();

        // Comportamiento
        logger.info(HEADER_BOX);
        logger.info("│           PATRONES DE COMPORTAMIENTO                    │");
        logger.info(FOOTER_BOX);
        demonstrateChainOfResponsibility();
        pause();
        demonstrateCommand();
        pause();
        demonstrateObserver();
        pause();
        demonstrateStrategy();

        logger.info("\n\n╔═══════════════════════════════════════════════════════╗");
        logger.info("║       ¡DEMOSTRACIÓN COMPLETADA EXITOSAMENTE!          ║");
        logger.info("║         Patrones de Diseño Implementados              ║");
        logger.info("╚═══════════════════════════════════════════════════════╝\n");
    }

    // ==================== PATRONES CREACIONALES ====================

    private static void demonstrateFactoryMethod() {
        printPatternHeader("FACTORY METHOD", CREATIONAL);
        logger.info("Permite crear objetos sin especificar la clase exacta.");
        logger.info("Contexto: Diferentes tipos de restaurantes crean diferentes platos.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- La clase abstracta 'Restaurant' define el método orderDish()");
        logger.info("- Cada subclase (ItalianRestaurant, PastaRestaurant, HealthyRestaurant)");
        logger.info("  implementa createDish() retornando un tipo específico de plato");
        logger.info("- El cliente usa la interfaz Restaurant sin conocer la clase concreta\n");

        Restaurant italian = new ItalianRestaurant();
        Restaurant pasta = new PastaRestaurant();
        Restaurant healthy = new HealthyRestaurant();

        italian.orderDish();
        pasta.orderDish();
        healthy.orderDish();

        printPatternFooter();
    }

    private static void demonstrateBuilder() {
        printPatternHeader("BUILDER", CREATIONAL);
        logger.info("Construye objetos complejos paso a paso.");
        logger.info("Contexto: Construcción de menús personalizados.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Menu.Builder permite construir un menú con métodos encadenados");
        logger.info("- Cada método retorna 'this' para permitir fluent interface");
        logger.info("- MenuDirector encapsula recetas predefinidas (menú familiar)");
        logger.info("- El método build() valida y crea el objeto Menu final\n");

        MenuDirector director = new MenuDirector();

        Menu familyMenu = director.createFamilyMenu();
        logger.info(familyMenu.toString());

        logger.info("\n{}\n", SEPARATOR_60);

        Menu customMenu = new Menu.Builder()
                .name("Menú Personalizado del Cliente")
                .addAppetizer("Camarones al ajillo", 14.99)
                .addMainCourse("Filete de salmón", 22.99)
                .addDessert("Cheesecake", 7.99)
                .addBeverage("Vino blanco", 18.99)
                .specialInstructions("Sin sal, preparar al vapor")
                .glutenFree(true)
                .build();

        logger.info(customMenu.toString());

        printPatternFooter();
    }

    private static void demonstrateSingleton() {
        printPatternHeader("SINGLETON", CREATIONAL);
        logger.info("Garantiza una única instancia de una clase.");
        logger.info("Contexto: Configuración global del restaurante.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- RestaurantConfig tiene constructor privado");
        logger.info("- getInstance() retorna siempre la misma instancia (thread-safe)");
        logger.info("- Usa inicialización temprana (eager initialization)");
        logger.info("- Centraliza configuración: nombre, impuesto, números de orden\n");

        RestaurantConfig config = RestaurantConfig.getInstance();
        logger.info(config.toString());

        logger.info("\n=== Generando números de orden ===");
        logger.info("Orden #{}", config.getNextOrderNumber());
        logger.info("Orden #{}", config.getNextOrderNumber());
        logger.info("Orden #{}", config.getNextOrderNumber());

        logger.info("\n=== Calculando impuestos ===");
        double subtotal = 50.00;
        double tax = config.calculateTax(subtotal);
        double total = config.calculateTotal(subtotal);
        logger.info(String.format("Subtotal: $%.2f", subtotal));
        logger.info(String.format("Impuesto: $%.2f", tax));
        logger.info(String.format("Total: $%.2f", total));

        // Demostrar que es la misma instancia
        RestaurantConfig config2 = RestaurantConfig.getInstance();
        boolean isSameInstance = (config == config2);
        logger.info("\n¿Es la misma instancia? {}", isSameInstance);

        printPatternFooter();
    }

    // ==================== PATRONES ESTRUCTURALES ====================

    private static void demonstrateDecorator() {
        printPatternHeader("DECORATOR", STRUCTURAL);
        logger.info("Agrega funcionalidad a objetos dinámicamente.");
        logger.info("Contexto: Personalización de bebidas con extras.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Beverage es el componente base (Coffee, Tea)");
        logger.info("- BeverageDecorator es la clase abstracta decoradora");
        logger.info("- Decoradores concretos (Milk, Sugar, Caramel) envuelven Beverage");
        logger.info("- Cada decorador agrega costo y modifica la descripción\n");

        // Café básico
        Beverage beverage1 = new Coffee();
        logger.info("{} - ${}", beverage1.getDescription(), beverage1.getCost());

        // Café con leche
        beverage1 = new MilkDecorator(beverage1);
        logger.info("{} - ${}", beverage1.getDescription(), beverage1.getCost());

        // Café con leche y azúcar
        beverage1 = new SugarDecorator(beverage1);
        logger.info("{} - ${}", beverage1.getDescription(), beverage1.getCost());

        logger.info("\n{}\n", SEPARATOR_60);

        // Bebida compleja
        Beverage beverage2 = new Coffee();
        beverage2 = new MilkDecorator(beverage2);
        beverage2 = new WhippedCreamDecorator(beverage2);
        beverage2 = new CaramelDecorator(beverage2);
        beverage2 = new SugarDecorator(beverage2);
        logger.info(beverage2.getDescription().toString());
        double finalPrice = beverage2.getCost();
        logger.info("Precio final: ${}", String.format("%.2f", finalPrice));

        logger.info("\n{}\n", SEPARATOR_60);

        // Té con miel (azúcar)
        Beverage tea = new Tea();
        tea = new SugarDecorator(tea);
        logger.info("{} - ${}", tea.getDescription(), tea.getCost());

        printPatternFooter();
    }

    private static void demonstrateFacade() {
        printPatternHeader("FACADE", STRUCTURAL);
        logger.info("Proporciona interfaz simplificada a un sistema complejo.");
        logger.info("Contexto: Sistema simplificado de procesamiento de órdenes.\n");

        RestaurantFacade facade = new RestaurantFacade();

        // Orden para comer en el local
        facade.placeOrder("ORD-2001", "Pizza Margherita + Refresco", 18.99, "Tarjeta de Crédito");

        // Orden para entrega a domicilio
        facade.placeDeliveryOrder("ORD-2002", "Pasta Carbonara + Ensalada", 22.50, 
                                  "Efectivo", "Calle Principal #123");

        // Notificar orden lista
        facade.notifyOrderComplete("ORD-2001");

        printPatternFooter();
    }

    // ==================== PATRONES DE COMPORTAMIENTO ====================

    private static void demonstrateChainOfResponsibility() {
        printPatternHeader("CHAIN OF RESPONSIBILITY", BEHAVIORAL);
        logger.info("Pasa solicitudes a través de una cadena de manejadores.");
        logger.info("Contexto: Sistema de descuentos con múltiples criterios.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- DiscountHandler es la clase abstracta con setNext()");
        logger.info("- Handlers: VIP, Loyalty, BulkOrder, Default (en ese orden)");
        logger.info("- Cada handler decide si procesa o pasa al siguiente");
        logger.info("- La solicitud viaja por la cadena hasta ser procesada\n");

        // Configurar cadena
        DiscountHandler vipHandler = new VIPDiscountHandler();
        DiscountHandler loyaltyHandler = new LoyaltyDiscountHandler();
        DiscountHandler bulkHandler = new BulkOrderDiscountHandler();
        DiscountHandler defaultHandler = new DefaultDiscountHandler();

        vipHandler.setNext(loyaltyHandler);
        loyaltyHandler.setNext(bulkHandler);
        bulkHandler.setNext(defaultHandler);

        // Probar diferentes solicitudes
        logger.info("=== Cliente VIP ===");
        DiscountRequest request1 = new DiscountRequest("VIP", 50.0, false, 5);
        double discount1 = vipHandler.handleDiscount(request1);
        logger.info("Descuento aplicado: {}%\n", discount1 * 100);

        logger.info("=== Cliente leal (10+ visitas) ===");
        DiscountRequest request2 = new DiscountRequest("REGULAR", 50.0, true, 15);
        double discount2 = vipHandler.handleDiscount(request2);
        logger.info("Descuento aplicado: {}%\n", discount2 * 100);

        logger.info("=== Orden grande (+$100) ===");
        DiscountRequest request3 = new DiscountRequest("REGULAR", 150.0, false, 3);
        double discount3 = vipHandler.handleDiscount(request3);
        logger.info("Descuento aplicado: {}%\n", discount3 * 100);

        logger.info("=== Cliente regular ===");
        DiscountRequest request4 = new DiscountRequest("REGULAR", 50.0, false, 2);
        double discount4 = vipHandler.handleDiscount(request4);
        logger.info("Descuento aplicado: {}%\n", discount4 * 100);

        printPatternFooter();
    }

    private static void demonstrateCommand() {
        printPatternHeader("COMMAND", BEHAVIORAL);
        logger.info("Encapsula solicitudes como objetos.");
        logger.info("Contexto: Sistema de órdenes con soporte de undo.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Command es la interfaz con execute() y undo()");
        logger.info("- Comandos concretos: AddItemCommand, RemoveItemCommand");
        logger.info("- Waiter (Invoker) ejecuta y almacena comandos");
        logger.info("- Permite deshacer operaciones con undoLastOrder()\n");

        KitchenOrder order = new KitchenOrder();
        Waiter waiter = new Waiter();

        logger.info("=== Tomando orden ===");
        waiter.takeOrder(new AddItemCommand(order, "Pizza Margherita"));
        waiter.takeOrder(new AddItemCommand(order, "Ensalada César"));
        waiter.takeOrder(new AddItemCommand(order, "Refresco"));

        logger.info("\n=== Cliente cambia de opinión ===");
        waiter.undoLastOrder();

        logger.info("\n=== Agregar otro item ===");
        waiter.takeOrder(new AddItemCommand(order, "Jugo natural"));

        logger.info("");
        waiter.takeOrder(new PrepareOrderCommand(order));

        waiter.showHistory();

        printPatternFooter();
    }

    private static void demonstrateObserver() {
        printPatternHeader("OBSERVER", BEHAVIORAL);
        logger.info("Notifica cambios a múltiples observadores.");
        logger.info("Contexto: Tracking de órdenes con múltiples suscriptores.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- OrderTracker (sujeto) mantiene lista de observadores");
        logger.info("- Observer: CustomerNotifier, KitchenDisplay, AnalyticsSystem");
        logger.info("- Al cambiar el estado con updateStatus(), notifica a todos");
        logger.info("- Cada observador reacciona según su responsabilidad\n");

        OrderTracker tracker = new OrderTracker("ORD-5001");

        // Registrar observadores
        tracker.attach(new CustomerNotifier("María García"));
        tracker.attach(new KitchenDisplay(1));
        tracker.attach(new AnalyticsSystem());

        // Cambios de estado
        tracker.updateStatus("CONFIRMADA", "Pago recibido, iniciando preparación");
        
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        tracker.updateStatus("EN_PREPARACIÓN", "El chef está preparando tu orden");
        
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        tracker.updateStatus("LISTA", "Tu orden está lista para recoger");

        printPatternFooter();
    }

    private static void demonstrateStrategy() {
        printPatternHeader("STRATEGY", BEHAVIORAL);
        logger.info("Define familia de algoritmos intercambiables.");
        logger.info("Contexto: Diferentes estrategias de precios.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- PricingStrategy es la interfaz de estrategia");
        logger.info("- Estrategias concretas: Regular, HappyHour, Weekend, Member");
        logger.info("- MenuItem contiene una referencia a PricingStrategy");
        logger.info("- Se puede cambiar la estrategia en tiempo de ejecución\n");

        MenuItem item = new MenuItem("Hamburguesa Deluxe", 15.00);

        logger.info("=== Precio Regular ===");
        item.setPricingStrategy(new RegularPricing());
        logger.info(item.getPriceInfo().toString());

        logger.info("\n=== Happy Hour ===");
        item.setPricingStrategy(new HappyHourPricing());
        logger.info(item.getPriceInfo().toString());

        logger.info("\n=== Fin de Semana ===");
        item.setPricingStrategy(new WeekendPricing());
        logger.info(item.getPriceInfo().toString());

        logger.info("\n=== Precio de Miembro ===");
        item.setPricingStrategy(new MemberPricing());
        logger.info(item.getPriceInfo().toString());

        printPatternFooter();
    }

    // ==================== UTILIDADES ====================

    private static void printPatternHeader(String patternName, String category) {
        String doubleEquals = "═".repeat(58);
        String headerText = String.format("%-54s", patternName + " (" + category + ")");
        logger.info("\n\n╔{}╗", doubleEquals);
        logger.info("║  {}  ║", headerText);
        logger.info("╚{}╝", doubleEquals);
    }

    private static void printPatternFooter() {
        logger.info("\n{}", SEPARATOR_60);
        logger.info("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void pause() {
        logger.info("\nPresione ENTER para continuar con el siguiente patrón...");
        scanner.nextLine();
    }

    private static int getIntInput() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}




