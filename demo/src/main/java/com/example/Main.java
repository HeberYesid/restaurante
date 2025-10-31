package com.example;

import com.example.patterns.creational.factorymethod.*;
import com.example.patterns.creational.abstractfactory.*;
import com.example.patterns.creational.builder.*;
import com.example.patterns.creational.prototype.*;
import com.example.patterns.creational.singleton.*;

import com.example.patterns.structural.adapter.*;
import com.example.patterns.structural.bridge.*;
import com.example.patterns.structural.composite.*;
import com.example.patterns.structural.decorator.*;
import com.example.patterns.structural.facade.*;
import com.example.patterns.structural.flyweight.*;
import com.example.patterns.structural.proxy.*;

import com.example.patterns.behavioral.chainofresponsibility.*;
import com.example.patterns.behavioral.command.*;
import com.example.patterns.behavioral.iterator.*;
import com.example.patterns.behavioral.mediator.*;
import com.example.patterns.behavioral.memento.*;
import com.example.patterns.behavioral.observer.*;
import com.example.patterns.behavioral.state.*;
import com.example.patterns.behavioral.strategy.*;
import com.example.patterns.behavioral.templatemethod.*;
import com.example.patterns.behavioral.visitor.*;

import java.util.Scanner;

/**
 * Aplicación principal que demuestra todos los patrones de diseño
 * en el contexto de un sistema de restaurante
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE RESTAURANTE - PATRONES DE DISEÑO       ║");
        System.out.println("║   Demostración de 23 Patrones de Diseño GoF         ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

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
                    System.out.println("\n¡Gracias por usar el sistema! Hasta pronto.");
                    running = false;
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        }
        
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MENÚ PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. Patrones Creacionales");
        System.out.println("2. Patrones Estructurales");
        System.out.println("3. Patrones de Comportamiento");
        System.out.println("4. Demostrar TODOS los patrones");
        System.out.println("0. Salir");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private static void showCreationalPatterns() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("              PATRONES CREACIONALES");
        System.out.println("─".repeat(60));
        System.out.println("1. Factory Method");
        System.out.println("2. Abstract Factory");
        System.out.println("3. Builder");
        System.out.println("4. Prototype");
        System.out.println("5. Singleton");
        System.out.println("0. Volver");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione un patrón: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateFactoryMethod();
                break;
            case 2:
                demonstrateAbstractFactory();
                break;
            case 3:
                demonstrateBuilder();
                break;
            case 4:
                demonstratePrototype();
                break;
            case 5:
                demonstrateSingleton();
                break;
        }
    }

    private static void showStructuralPatterns() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("              PATRONES ESTRUCTURALES");
        System.out.println("─".repeat(60));
        System.out.println("1. Adapter");
        System.out.println("2. Bridge");
        System.out.println("3. Composite");
        System.out.println("4. Decorator");
        System.out.println("5. Facade");
        System.out.println("6. Flyweight");
        System.out.println("7. Proxy");
        System.out.println("0. Volver");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione un patrón: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateAdapter();
                break;
            case 2:
                demonstrateBridge();
                break;
            case 3:
                demonstrateComposite();
                break;
            case 4:
                demonstrateDecorator();
                break;
            case 5:
                demonstrateFacade();
                break;
            case 6:
                demonstrateFlyweight();
                break;
            case 7:
                demonstrateProxy();
                break;
        }
    }

    private static void showBehavioralPatterns() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("           PATRONES DE COMPORTAMIENTO");
        System.out.println("─".repeat(60));
        System.out.println("1. Chain of Responsibility");
        System.out.println("2. Command");
        System.out.println("3. Iterator");
        System.out.println("4. Mediator");
        System.out.println("5. Memento");
        System.out.println("6. Observer");
        System.out.println("7. State");
        System.out.println("8. Strategy");
        System.out.println("9. Template Method");
        System.out.println("10. Visitor");
        System.out.println("0. Volver");
        System.out.println("─".repeat(60));
        System.out.print("Seleccione un patrón: ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                demonstrateChainOfResponsibility();
                break;
            case 2:
                demonstrateCommand();
                break;
            case 3:
                demonstrateIterator();
                break;
            case 4:
                demonstrateMediator();
                break;
            case 5:
                demonstrateMemento();
                break;
            case 6:
                demonstrateObserver();
                break;
            case 7:
                demonstrateState();
                break;
            case 8:
                demonstrateStrategy();
                break;
            case 9:
                demonstrateTemplateMethod();
                break;
            case 10:
                demonstrateVisitor();
                break;
        }
    }

    private static void showAllPatterns() {
        System.out.println("\n\n");
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      DEMOSTRACIÓN COMPLETA DE TODOS LOS PATRONES      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        // Creacionales
        System.out.println("\n\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│               PATRONES CREACIONALES                     │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        demonstrateFactoryMethod();
        pause();
        demonstrateAbstractFactory();
        pause();
        demonstrateBuilder();
        pause();
        demonstratePrototype();
        pause();
        demonstrateSingleton();
        pause();

        // Estructurales
        System.out.println("\n\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│               PATRONES ESTRUCTURALES                    │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        demonstrateAdapter();
        pause();
        demonstrateBridge();
        pause();
        demonstrateComposite();
        pause();
        demonstrateDecorator();
        pause();
        demonstrateFacade();
        pause();
        demonstrateFlyweight();
        pause();
        demonstrateProxy();
        pause();

        // Comportamiento
        System.out.println("\n\n┌─────────────────────────────────────────────────────────┐");
        System.out.println("│           PATRONES DE COMPORTAMIENTO                    │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        demonstrateChainOfResponsibility();
        pause();
        demonstrateCommand();
        pause();
        demonstrateIterator();
        pause();
        demonstrateMediator();
        pause();
        demonstrateMemento();
        pause();
        demonstrateObserver();
        pause();
        demonstrateState();
        pause();
        demonstrateStrategy();
        pause();
        demonstrateTemplateMethod();
        pause();
        demonstrateVisitor();

        System.out.println("\n\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║       ¡DEMOSTRACIÓN COMPLETADA EXITOSAMENTE!          ║");
        System.out.println("║         23 Patrones de Diseño Implementados            ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
    }

    // ==================== PATRONES CREACIONALES ====================

    private static void demonstrateFactoryMethod() {
        printPatternHeader("FACTORY METHOD", "Creacional");
        System.out.println("Permite crear objetos sin especificar la clase exacta.");
        System.out.println("Contexto: Diferentes tipos de restaurantes crean diferentes platos.\n");

        Restaurant italian = new ItalianRestaurant();
        Restaurant pasta = new PastaRestaurant();
        Restaurant healthy = new HealthyRestaurant();

        italian.orderDish();
        pasta.orderDish();
        healthy.orderDish();

        printPatternFooter();
    }

    private static void demonstrateAbstractFactory() {
        printPatternHeader("ABSTRACT FACTORY", "Creacional");
        System.out.println("Crea familias de objetos relacionados sin especificar sus clases concretas.");
        System.out.println("Contexto: Fábricas de ingredientes premium y económicos.\n");

        IngredientFactory premiumFactory = new PremiumIngredientFactory();
        IngredientFactory budgetFactory = new BudgetIngredientFactory();

        System.out.println("=== INGREDIENTES PREMIUM ===");
        Meat premiumMeat = premiumFactory.createMeat();
        Vegetable premiumVeg = premiumFactory.createVegetable();
        Cheese premiumCheese = premiumFactory.createCheese();
        
        System.out.println("Carne: " + premiumMeat.getName() + " ($" + premiumMeat.getCost() + ") - " + premiumMeat.getOrigin());
        System.out.println("Vegetal: " + premiumVeg.getName() + " ($" + premiumVeg.getCost() + ") - Orgánico: " + premiumVeg.isOrganic());
        System.out.println("Queso: " + premiumCheese.getName() + " ($" + premiumCheese.getCost() + ") - Añejado " + premiumCheese.getAgingMonths() + " meses");

        System.out.println("\n=== INGREDIENTES ECONÓMICOS ===");
        Meat budgetMeat = budgetFactory.createMeat();
        Vegetable budgetVeg = budgetFactory.createVegetable();
        Cheese budgetCheese = budgetFactory.createCheese();
        
        System.out.println("Carne: " + budgetMeat.getName() + " ($" + budgetMeat.getCost() + ") - " + budgetMeat.getOrigin());
        System.out.println("Vegetal: " + budgetVeg.getName() + " ($" + budgetVeg.getCost() + ") - Orgánico: " + budgetVeg.isOrganic());
        System.out.println("Queso: " + budgetCheese.getName() + " ($" + budgetCheese.getCost() + ") - Añejado " + budgetCheese.getAgingMonths() + " meses");

        printPatternFooter();
    }

    private static void demonstrateBuilder() {
        printPatternHeader("BUILDER", "Creacional");
        System.out.println("Construye objetos complejos paso a paso.");
        System.out.println("Contexto: Construcción de menús personalizados.\n");

        MenuDirector director = new MenuDirector();

        Menu familyMenu = director.createFamilyMenu();
        System.out.println(familyMenu);

        System.out.println("\n" + "─".repeat(60) + "\n");

        Menu customMenu = new Menu.Builder()
                .name("Menú Personalizado del Cliente")
                .addAppetizer("Camarones al ajillo", 14.99)
                .addMainCourse("Filete de salmón", 22.99)
                .addDessert("Cheesecake", 7.99)
                .addBeverage("Vino blanco", 18.99)
                .specialInstructions("Sin sal, preparar al vapor")
                .glutenFree(true)
                .build();

        System.out.println(customMenu);

        printPatternFooter();
    }

    private static void demonstratePrototype() {
        printPatternHeader("PROTOTYPE", "Creacional");
        System.out.println("Clona objetos existentes sin depender de sus clases.");
        System.out.println("Contexto: Clonación de recetas con modificaciones.\n");

        RecipeRegistry registry = new RecipeRegistry();
        registry.initializeDefaultRecipes();

        System.out.println("=== RECETA ORIGINAL ===");
        Recipe pizzaRecipe = registry.getRecipe("pizza");
        System.out.println(pizzaRecipe);

        System.out.println("\n=== RECETA CLONADA Y MODIFICADA ===");
        Recipe largePizza = pizzaRecipe.cloneWithModifications("Pizza Margherita Familiar", 8);
        System.out.println(largePizza);

        System.out.println("\n=== OTRA VARIANTE ===");
        Recipe personalPizza = pizzaRecipe.cloneWithModifications("Pizza Personal", 1);
        System.out.println(personalPizza);

        printPatternFooter();
    }

    private static void demonstrateSingleton() {
        printPatternHeader("SINGLETON", "Creacional");
        System.out.println("Garantiza una única instancia de una clase.");
        System.out.println("Contexto: Configuración global del restaurante.\n");

        RestaurantConfig config = RestaurantConfig.getInstance();
        System.out.println(config);

        System.out.println("\n=== Generando números de orden ===");
        System.out.println("Orden #" + config.getNextOrderNumber());
        System.out.println("Orden #" + config.getNextOrderNumber());
        System.out.println("Orden #" + config.getNextOrderNumber());

        System.out.println("\n=== Calculando impuestos ===");
        double subtotal = 50.00;
        double tax = config.calculateTax(subtotal);
        double total = config.calculateTotal(subtotal);
        System.out.println(String.format("Subtotal: $%.2f", subtotal));
        System.out.println(String.format("Impuesto: $%.2f", tax));
        System.out.println(String.format("Total: $%.2f", total));

        // Demostrar que es la misma instancia
        RestaurantConfig config2 = RestaurantConfig.getInstance();
        System.out.println("\n¿Es la misma instancia? " + (config == config2));

        printPatternFooter();
    }

    // ==================== PATRONES ESTRUCTURALES ====================

    private static void demonstrateAdapter() {
        printPatternHeader("ADAPTER", "Estructural");
        System.out.println("Permite que interfaces incompatibles trabajen juntas.");
        System.out.println("Contexto: Adaptar sistema legacy de pagos a interfaz moderna.\n");

        LegacyPOSSystem legacySystem = new LegacyPOSSystem();
        ModernPaymentProcessor processor = new PaymentAdapter(legacySystem);

        PaymentRequest request = new PaymentRequest("4532123456789012", 125.50, "Juan Pérez");

        System.out.println("=== Validando método de pago ===");
        boolean isValid = processor.validatePaymentMethod(request);
        System.out.println("Válido: " + isValid);

        System.out.println("\n=== Procesando pago ===");
        PaymentResult result = processor.processPayment(request);
        System.out.println(result);

        printPatternFooter();
    }

    private static void demonstrateBridge() {
        printPatternHeader("BRIDGE", "Estructural");
        System.out.println("Separa abstracción de implementación.");
        System.out.println("Contexto: Notificaciones del restaurante con diferentes métodos de envío.\n");

        // Crear diferentes implementaciones
        NotificationSender email = new EmailSender();
        NotificationSender sms = new SMSSender();
        NotificationSender push = new PushNotificationSender();

        // Crear notificaciones con diferentes implementaciones
        System.out.println("=== CONFIRMACIÓN DE ORDEN ===");
        Notification orderConfirmation = new OrderConfirmationNotification(email, "ORD-1001", 45.99);
        orderConfirmation.notify("cliente@email.com");

        System.out.println("=== RESERVACIÓN ===");
        Notification reservation = new ReservationNotification(sms, "2024-12-25", "19:00", 5);
        reservation.notify("+1234567890");

        System.out.println("=== PROMOCIÓN ===");
        Notification promotion = new PromotionNotification(push, "2x1 en Pizzas", 50);
        promotion.notify("device-token-123");

        System.out.println("\n=== Cambiando método de envío dinámicamente ===");
        orderConfirmation.changeSender(sms);
        orderConfirmation.notify("+0987654321");

        printPatternFooter();
    }

    private static void demonstrateComposite() {
        printPatternHeader("COMPOSITE", "Estructural");
        System.out.println("Compone objetos en estructuras de árbol.");
        System.out.println("Contexto: Estructura jerárquica del menú del restaurante.\n");

        // Crear menú completo
        MenuCategory mainMenu = new MenuCategory("Menú Principal", "Nuestro delicioso menú");

        // Entradas
        MenuCategory appetizers = new MenuCategory("Entradas", "Para comenzar");
        appetizers.add(new com.example.patterns.structural.composite.MenuItem("Nachos", "Nachos con queso y jalapeños", 8.99, false));
        appetizers.add(new com.example.patterns.structural.composite.MenuItem("Ensalada César", "Lechuga, pollo, crutones", 7.99, true));

        // Platos principales
        MenuCategory mainCourses = new MenuCategory("Platos Principales", "Nuestras especialidades");
        mainCourses.add(new com.example.patterns.structural.composite.MenuItem("Filete de res", "250g de res premium", 24.99, false));
        mainCourses.add(new com.example.patterns.structural.composite.MenuItem("Pasta Primavera", "Pasta con vegetales frescos", 14.99, true));
        mainCourses.add(new com.example.patterns.structural.composite.MenuItem("Salmón a la parrilla", "Con vegetales al vapor", 19.99, false));

        // Postres
        MenuCategory desserts = new MenuCategory("Postres", "Dulce final");
        desserts.add(new com.example.patterns.structural.composite.MenuItem("Tiramisú", "Clásico italiano", 6.99, true));
        desserts.add(new com.example.patterns.structural.composite.MenuItem("Helado artesanal", "3 bolas a elección", 5.99, true));

        // Armar menú completo
        mainMenu.add(appetizers);
        mainMenu.add(mainCourses);
        mainMenu.add(desserts);

        // Mostrar menú
        mainMenu.print("");

        System.out.println("\n=== Información del menú ===");
        System.out.println("Precio total del menú completo: $" + String.format("%.2f", mainMenu.getPrice()));

        printPatternFooter();
    }

    private static void demonstrateDecorator() {
        printPatternHeader("DECORATOR", "Estructural");
        System.out.println("Agrega funcionalidad a objetos dinámicamente.");
        System.out.println("Contexto: Personalización de bebidas con extras.\n");

        // Café básico
        Beverage beverage1 = new Coffee();
        System.out.println(beverage1.getDescription() + " - $" + beverage1.getCost());

        // Café con leche
        beverage1 = new MilkDecorator(beverage1);
        System.out.println(beverage1.getDescription() + " - $" + beverage1.getCost());

        // Café con leche y azúcar
        beverage1 = new SugarDecorator(beverage1);
        System.out.println(beverage1.getDescription() + " - $" + beverage1.getCost());

        System.out.println("\n" + "─".repeat(50) + "\n");

        // Bebida compleja
        Beverage beverage2 = new Coffee();
        beverage2 = new MilkDecorator(beverage2);
        beverage2 = new WhippedCreamDecorator(beverage2);
        beverage2 = new CaramelDecorator(beverage2);
        beverage2 = new SugarDecorator(beverage2);
        System.out.println(beverage2.getDescription());
        System.out.println("Precio final: $" + String.format("%.2f", beverage2.getCost()));

        System.out.println("\n" + "─".repeat(50) + "\n");

        // Té con miel (azúcar)
        Beverage tea = new Tea();
        tea = new SugarDecorator(tea);
        System.out.println(tea.getDescription() + " - $" + tea.getCost());

        printPatternFooter();
    }

    private static void demonstrateFacade() {
        printPatternHeader("FACADE", "Estructural");
        System.out.println("Proporciona interfaz simplificada a un sistema complejo.");
        System.out.println("Contexto: Sistema simplificado de procesamiento de órdenes.\n");

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

    private static void demonstrateFlyweight() {
        printPatternHeader("FLYWEIGHT", "Estructural");
        System.out.println("Comparte objetos para soportar grandes cantidades eficientemente.");
        System.out.println("Contexto: Optimización de memoria para tipos de mesas.\n");

        System.out.println("Creando 20 mesas en el restaurante...\n");

        Table[] tables = new Table[20];

        // Crear mesas reutilizando tipos
        for (int i = 0; i < 5; i++) {
            TableType type = TableTypeFactory.getTableType("Pequeña", 2, "Interior", false);
            tables[i] = new Table(i + 1, type);
        }

        for (int i = 5; i < 15; i++) {
            TableType type = TableTypeFactory.getTableType("Mediana", 4, "Interior", false);
            tables[i] = new Table(i + 1, type);
        }

        for (int i = 15; i < 20; i++) {
            TableType type = TableTypeFactory.getTableType("Grande", 6, "Terraza", true);
            tables[i] = new Table(i + 1, type);
        }

        // Ocupar algunas mesas
        tables[0].occupy();
        tables[5].occupy();
        tables[15].occupy();

        // Mostrar información de algunas mesas
        System.out.println("\n=== Mesas seleccionadas ===");
        tables[0].displayInfo();
        tables[5].displayInfo();
        tables[10].displayInfo();
        tables[15].displayInfo();

        TableTypeFactory.printStatistics();

        printPatternFooter();
    }

    private static void demonstrateProxy() {
        printPatternHeader("PROXY", "Estructural");
        System.out.println("Proporciona sustituto o marcador de posición para otro objeto.");
        System.out.println("Contexto: Carga lazy de imágenes del menú y control de acceso.\n");

        System.out.println("=== VIRTUAL PROXY (Lazy Loading) ===");
        MenuImage image1 = new MenuImageProxy("menu_especial.jpg");
        System.out.println("Proxy creado, imagen NO cargada todavía\n");

        System.out.println("Primera visualización:");
        image1.display();

        System.out.println("\nSegunda visualización (imagen ya en memoria):");
        image1.display();

        System.out.println("\n" + "─".repeat(50) + "\n");

        System.out.println("=== PROTECTION PROXY (Control de Acceso) ===");
        MenuImage confidentialMenu1 = new ProtectedMenuImage("menu_costos.jpg", "ADMIN");
        confidentialMenu1.display();

        System.out.println();
        MenuImage confidentialMenu2 = new ProtectedMenuImage("menu_costos.jpg", "WAITER");
        confidentialMenu2.display();

        printPatternFooter();
    }

    // ==================== PATRONES DE COMPORTAMIENTO ====================

    private static void demonstrateChainOfResponsibility() {
        printPatternHeader("CHAIN OF RESPONSIBILITY", "Comportamiento");
        System.out.println("Pasa solicitudes a través de una cadena de manejadores.");
        System.out.println("Contexto: Sistema de descuentos con múltiples criterios.\n");

        // Configurar cadena
        DiscountHandler vipHandler = new VIPDiscountHandler();
        DiscountHandler loyaltyHandler = new LoyaltyDiscountHandler();
        DiscountHandler bulkHandler = new BulkOrderDiscountHandler();
        DiscountHandler defaultHandler = new DefaultDiscountHandler();

        vipHandler.setNext(loyaltyHandler);
        loyaltyHandler.setNext(bulkHandler);
        bulkHandler.setNext(defaultHandler);

        // Probar diferentes solicitudes
        System.out.println("=== Cliente VIP ===");
        DiscountRequest request1 = new DiscountRequest("VIP", 50.0, false, 5);
        double discount1 = vipHandler.handleDiscount(request1);
        System.out.println("Descuento aplicado: " + (discount1 * 100) + "%\n");

        System.out.println("=== Cliente leal (10+ visitas) ===");
        DiscountRequest request2 = new DiscountRequest("REGULAR", 50.0, true, 15);
        double discount2 = vipHandler.handleDiscount(request2);
        System.out.println("Descuento aplicado: " + (discount2 * 100) + "%\n");

        System.out.println("=== Orden grande (+$100) ===");
        DiscountRequest request3 = new DiscountRequest("REGULAR", 150.0, false, 3);
        double discount3 = vipHandler.handleDiscount(request3);
        System.out.println("Descuento aplicado: " + (discount3 * 100) + "%\n");

        System.out.println("=== Cliente regular ===");
        DiscountRequest request4 = new DiscountRequest("REGULAR", 50.0, false, 2);
        double discount4 = vipHandler.handleDiscount(request4);
        System.out.println("Descuento aplicado: " + (discount4 * 100) + "%\n");

        printPatternFooter();
    }

    private static void demonstrateCommand() {
        printPatternHeader("COMMAND", "Comportamiento");
        System.out.println("Encapsula solicitudes como objetos.");
        System.out.println("Contexto: Sistema de órdenes con soporte de undo.\n");

        KitchenOrder order = new KitchenOrder();
        Waiter waiter = new Waiter();

        System.out.println("=== Tomando orden ===");
        waiter.takeOrder(new AddItemCommand(order, "Pizza Margherita"));
        waiter.takeOrder(new AddItemCommand(order, "Ensalada César"));
        waiter.takeOrder(new AddItemCommand(order, "Refresco"));

        System.out.println("\n=== Cliente cambia de opinión ===");
        waiter.undoLastOrder();

        System.out.println("\n=== Agregar otro item ===");
        waiter.takeOrder(new AddItemCommand(order, "Jugo natural"));

        System.out.println();
        waiter.takeOrder(new PrepareOrderCommand(order));

        waiter.showHistory();

        printPatternFooter();
    }

    private static void demonstrateIterator() {
        printPatternHeader("ITERATOR", "Comportamiento");
        System.out.println("Accede secuencialmente a elementos de una colección.");
        System.out.println("Contexto: Iteración sobre platos especiales del día.\n");

        DailyMenuCollection menu = new DailyMenuCollection();
        menu.addSpecial(new DailySpecial("Tacos al Pastor", "Mexicana", 12.99, "Martes"));
        menu.addSpecial(new DailySpecial("Sushi Variado", "Japonesa", 18.99, "Miércoles"));
        menu.addSpecial(new DailySpecial("Paella", "Española", 22.99, "Jueves"));
        menu.addSpecial(new DailySpecial("Pizza Napolitana", "Italiana", 14.99, "Viernes"));
        menu.addSpecial(new DailySpecial("Pad Thai", "Tailandesa", 13.99, "Sábado"));

        System.out.println("=== TODOS LOS ESPECIALES ===");
        MenuIterator iterator = menu.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n=== SOLO COMIDA ITALIANA ===");
        MenuIterator italianIterator = menu.createFilteredIterator("Italiana");
        while (italianIterator.hasNext()) {
            System.out.println(italianIterator.next());
        }

        printPatternFooter();
    }

    private static void demonstrateMediator() {
        printPatternHeader("MEDIATOR", "Comportamiento");
        System.out.println("Reduce dependencias entre objetos mediante un mediador.");
        System.out.println("Contexto: Coordinación entre departamentos del restaurante.\n");

        RestaurantCoordinator mediator = new RestaurantCoordinator();

        FrontDesk frontDesk = new FrontDesk(mediator);
        Kitchen kitchen = new Kitchen(mediator);
        DeliveryService delivery = new DeliveryService(mediator);

        mediator.registerComponent(frontDesk);
        mediator.registerComponent(kitchen);
        mediator.registerComponent(delivery);

        System.out.println("\n=== Flujo de una orden ===");
        frontDesk.receiveOrder("Pizza + Bebida para mesa 5");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        kitchen.completeOrder("ORD-3001");
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        delivery.deliverOrder("ORD-3001");

        printPatternFooter();
    }

    private static void demonstrateMemento() {
        printPatternHeader("MEMENTO", "Comportamiento");
        System.out.println("Captura y restaura el estado interno de un objeto.");
        System.out.println("Contexto: Historial de cambios en una orden.\n");

        com.example.patterns.behavioral.memento.Order order = 
            new com.example.patterns.behavioral.memento.Order("ORD-4001");
        OrderHistory history = new OrderHistory();

        System.out.println("=== Estado inicial ===");
        System.out.println(order);
        history.save(order);

        System.out.println("\n=== Agregando items ===");
        order.addItem("Pizza", 15.99);
        System.out.println(order);
        history.save(order);

        order.addItem("Refresco", 2.99);
        System.out.println(order);
        history.save(order);

        order.setStatus("CONFIRMADA");
        System.out.println(order);
        history.save(order);

        System.out.println("\n=== Cliente quiere deshacer cambios ===");
        history.undo(order);
        System.out.println(order);

        history.undo(order);
        System.out.println(order);

        printPatternFooter();
    }

    private static void demonstrateObserver() {
        printPatternHeader("OBSERVER", "Comportamiento");
        System.out.println("Notifica cambios a múltiples observadores.");
        System.out.println("Contexto: Tracking de órdenes con múltiples suscriptores.\n");

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
            e.printStackTrace();
        }
        
        tracker.updateStatus("EN_PREPARACIÓN", "El chef está preparando tu orden");
        
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        tracker.updateStatus("LISTA", "Tu orden está lista para recoger");

        printPatternFooter();
    }

    private static void demonstrateState() {
        printPatternHeader("STATE", "Comportamiento");
        System.out.println("Cambia comportamiento según el estado interno.");
        System.out.println("Contexto: Estados de una mesa del restaurante.\n");

        TableContext table = new TableContext(10);

        System.out.println("\n=== Flujo normal de una mesa ===");
        table.reserve();
        System.out.println("Estado actual: " + table.getCurrentState());

        System.out.println();
        table.occupy();
        System.out.println("Estado actual: " + table.getCurrentState());

        System.out.println();
        table.free();
        System.out.println("Estado actual: " + table.getCurrentState());

        System.out.println();
        table.clean();
        System.out.println("Estado actual: " + table.getCurrentState());

        System.out.println("\n=== Intentando acciones inválidas ===");
        table.clean(); // Ya está limpia
        table.occupy(); // Ocupar directamente
        table.reserve(); // No se puede reservar ocupada

        printPatternFooter();
    }

    private static void demonstrateStrategy() {
        printPatternHeader("STRATEGY", "Comportamiento");
        System.out.println("Define familia de algoritmos intercambiables.");
        System.out.println("Contexto: Diferentes estrategias de precios.\n");

        com.example.patterns.behavioral.strategy.MenuItem item = 
            new com.example.patterns.behavioral.strategy.MenuItem("Hamburguesa Deluxe", 15.00);

        System.out.println("=== Precio Regular ===");
        item.setPricingStrategy(new RegularPricing());
        System.out.println(item.getPriceInfo());

        System.out.println("\n=== Happy Hour ===");
        item.setPricingStrategy(new HappyHourPricing());
        System.out.println(item.getPriceInfo());

        System.out.println("\n=== Fin de Semana ===");
        item.setPricingStrategy(new WeekendPricing());
        System.out.println(item.getPriceInfo());

        System.out.println("\n=== Precio de Miembro ===");
        item.setPricingStrategy(new MemberPricing());
        System.out.println(item.getPriceInfo());

        printPatternFooter();
    }

    private static void demonstrateTemplateMethod() {
        printPatternHeader("TEMPLATE METHOD", "Comportamiento");
        System.out.println("Define esqueleto de algoritmo, delegando pasos a subclases.");
        System.out.println("Contexto: Proceso de preparación de diferentes comidas.\n");

        System.out.println("=== Preparando Hamburguesa ===");
        MealPreparation burger = new BurgerPreparation();
        burger.prepareMeal();

        System.out.println("\n=== Preparando Ensalada ===");
        MealPreparation salad = new SaladPreparation();
        salad.prepareMeal();

        System.out.println("\n=== Preparando Pasta (con salsa) ===");
        MealPreparation pastaWithSauce = new PastaPreparation(true);
        pastaWithSauce.prepareMeal();

        printPatternFooter();
    }

    private static void demonstrateVisitor() {
        printPatternHeader("VISITOR", "Comportamiento");
        System.out.println("Separa algoritmos de objetos sobre los que operan.");
        System.out.println("Contexto: Operaciones sobre items del menú.\n");

        // Crear items del menú
        VisitableMenuItem[] order = {
            new Appetizer("Nachos", 8.99, true),
            new MainCourse("Filete de res", 24.99, 650),
            new Dessert("Tiramisú", 6.99, 45),
            new Drink("Limonada", 3.99, 350)
        };

        // Visitor para calcular precio
        System.out.println("=== CÁLCULO DE PRECIO ===");
        PriceCalculatorVisitor priceCalculator = new PriceCalculatorVisitor();
        for (VisitableMenuItem item : order) {
            item.accept(priceCalculator);
        }
        System.out.println("\nPrecio total: $" + String.format("%.2f", priceCalculator.getTotalPrice()));

        System.out.println("\n" + "─".repeat(50) + "\n");

        // Visitor para análisis nutricional
        System.out.println("=== ANÁLISIS NUTRICIONAL ===");
        NutritionalAnalysisVisitor nutritionAnalysis = new NutritionalAnalysisVisitor();
        for (VisitableMenuItem item : order) {
            item.accept(nutritionAnalysis);
        }
        System.out.println("\nTotal calorías: " + nutritionAnalysis.getTotalCalories() + " kcal");
        System.out.println("Total azúcar: " + nutritionAnalysis.getTotalSugar() + "g");

        printPatternFooter();
    }

    // ==================== UTILIDADES ====================

    private static void printPatternHeader(String patternName, String category) {
        System.out.println("\n\n" + "╔" + "═".repeat(58) + "╗");
        System.out.println("║  " + String.format("%-54s", patternName + " (" + category + ")") + "  ║");
        System.out.println("╚" + "═".repeat(58) + "╝");
    }

    private static void printPatternFooter() {
        System.out.println("\n" + "─".repeat(60));
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void pause() {
        System.out.print("\nPresione ENTER para continuar con el siguiente patrón...");
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