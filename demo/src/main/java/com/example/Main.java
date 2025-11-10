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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Aplicación principal que demuestra todos los patrones de diseño
 * en el contexto de un sistema de restaurante
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String APPLY_SECTION_HEADER = ">>> CÓMO SE APLICA:";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        logger.info("╔═══════════════════════════════════════════════════════╗");
        logger.info("║   SISTEMA DE RESTAURANTE - PATRONES DE DISEÑO       ║");
        logger.info("║   Demostración de 23 Patrones de Diseño GoF         ║");
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
            }
        }
        
        scanner.close();
    }

    private static void showMainMenu() {
        String separator = "=".repeat(60);
        logger.info("\n{}", separator);
        logger.info("                    MENÚ PRINCIPAL");
        logger.info(separator);
        logger.info("1. Patrones Creacionales");
        logger.info("2. Patrones Estructurales");
        logger.info("3. Patrones de Comportamiento");
        logger.info("4. Demostrar TODOS los patrones");
        logger.info("0. Salir");
        logger.info(separator);
        logger.info("Seleccione una opción: ");
    }

    private static void showCreationalPatterns() {
        String separator = "─".repeat(60);
        logger.info("\n{}", separator);
        logger.info("              PATRONES CREACIONALES");
        logger.info(separator);
        logger.info("1. Factory Method");
        logger.info("2. Abstract Factory");
        logger.info("3. Builder");
        logger.info("4. Prototype");
        logger.info("5. Singleton");
        logger.info("0. Volver");
        logger.info(separator);
        logger.info("Seleccione un patrón: ");

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
        String separator = "─".repeat(60);
        logger.info("\n{}", separator);
        logger.info("              PATRONES ESTRUCTURALES");
        logger.info(separator);
        logger.info("1. Adapter");
        logger.info("2. Bridge");
        logger.info("3. Composite");
        logger.info("4. Decorator");
        logger.info("5. Facade");
        logger.info("6. Flyweight");
        logger.info("7. Proxy");
        logger.info("0. Volver");
        logger.info(separator);
        logger.info("Seleccione un patrón: ");

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
        String separator = "─".repeat(60);
        logger.info("\n{}", separator);
        logger.info("           PATRONES DE COMPORTAMIENTO");
        logger.info(separator);
        logger.info("1. Chain of Responsibility");
        logger.info("2. Command");
        logger.info("3. Iterator");
        logger.info("4. Mediator");
        logger.info("5. Memento");
        logger.info("6. Observer");
        logger.info("7. State");
        logger.info("8. Strategy");
        logger.info("9. Template Method");
        logger.info("10. Visitor");
        logger.info("0. Volver");
        logger.info(separator);
        logger.info("Seleccione un patrón: ");

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
        logger.info("\n\n");
        logger.info("╔═══════════════════════════════════════════════════════╗");
        logger.info("║      DEMOSTRACIÓN COMPLETA DE TODOS LOS PATRONES      ║");
        logger.info("╚═══════════════════════════════════════════════════════╝");

        // Creacionales
        logger.info("\n\n┌─────────────────────────────────────────────────────────┐");
        logger.info("│               PATRONES CREACIONALES                     │");
        logger.info("└─────────────────────────────────────────────────────────┘");
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
        logger.info("\n\n┌─────────────────────────────────────────────────────────┐");
        logger.info("│               PATRONES ESTRUCTURALES                    │");
        logger.info("└─────────────────────────────────────────────────────────┘");
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
        logger.info("\n\n┌─────────────────────────────────────────────────────────┐");
        logger.info("│           PATRONES DE COMPORTAMIENTO                    │");
        logger.info("└─────────────────────────────────────────────────────────┘");
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

        logger.info("\n\n╔═══════════════════════════════════════════════════════╗");
        logger.info("║       ¡DEMOSTRACIÓN COMPLETADA EXITOSAMENTE!          ║");
        logger.info("║         23 Patrones de Diseño Implementados            ║");
        logger.info("╚═══════════════════════════════════════════════════════╝\n");
    }

    // ==================== PATRONES CREACIONALES ====================

    private static void demonstrateFactoryMethod() {
        printPatternHeader("FACTORY METHOD", "Creacional");
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

    private static void demonstrateAbstractFactory() {
        printPatternHeader("ABSTRACT FACTORY", "Creacional");
        logger.info("Crea familias de objetos relacionados sin especificar sus clases concretas.");
        logger.info("Contexto: Fábricas de ingredientes premium y económicos.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- IngredientFactory es la interfaz abstracta con métodos create*()");
        logger.info("- PremiumIngredientFactory crea ingredientes de alta calidad");
        logger.info("- BudgetIngredientFactory crea ingredientes económicos");
        logger.info("- Garantiza que los ingredientes de una familia sean compatibles\n");

        IngredientFactory premiumFactory = new PremiumIngredientFactory();
        IngredientFactory budgetFactory = new BudgetIngredientFactory();

        logger.info("=== INGREDIENTES PREMIUM ===");
        Meat premiumMeat = premiumFactory.createMeat();
        Vegetable premiumVeg = premiumFactory.createVegetable();
        Cheese premiumCheese = premiumFactory.createCheese();
        
        logger.info("Carne: {} (${}) - {}", premiumMeat.getName(), premiumMeat.getCost(), premiumMeat.getOrigin());
        logger.info("Vegetal: {} (${}) - Orgánico: {}", premiumVeg.getName(), premiumVeg.getCost(), premiumVeg.isOrganic());
        logger.info("Queso: {} (${}) - Añejado {} meses", premiumCheese.getName(), premiumCheese.getCost(), premiumCheese.getAgingMonths());

        logger.info("\n=== INGREDIENTES ECONÓMICOS ===");
        Meat budgetMeat = budgetFactory.createMeat();
        Vegetable budgetVeg = budgetFactory.createVegetable();
        Cheese budgetCheese = budgetFactory.createCheese();
        
        logger.info("Carne: {} (${}) - {}", budgetMeat.getName(), budgetMeat.getCost(), budgetMeat.getOrigin());
        logger.info("Vegetal: {} (${}) - Orgánico: {}", budgetVeg.getName(), budgetVeg.getCost(), budgetVeg.isOrganic());
        logger.info("Queso: {} (${}) - Añejado {} meses", budgetCheese.getName(), budgetCheese.getCost(), budgetCheese.getAgingMonths());

        printPatternFooter();
    }

    private static void demonstrateBuilder() {
        printPatternHeader("BUILDER", "Creacional");
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

        String separator60 = "─".repeat(60);
        logger.info("\n{}\n", separator60);

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

    private static void demonstratePrototype() {
        printPatternHeader("PROTOTYPE", "Creacional");
        logger.info("Clona objetos existentes sin depender de sus clases.");
        logger.info("Contexto: Clonación de recetas con modificaciones.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Recipe implementa Cloneable y sobrescribe clone()");
        logger.info("- RecipeRegistry almacena prototipos de recetas base");
        logger.info("- cloneWithModifications() crea copias y las personaliza");
        logger.info("- Evita crear recetas desde cero, solo modifica las existentes\n");

        RecipeRegistry registry = new RecipeRegistry();
        registry.initializeDefaultRecipes();

        logger.info("=== RECETA ORIGINAL ===");
        Recipe pizzaRecipe = registry.getRecipe("pizza");
        logger.info(pizzaRecipe.toString());

        logger.info("\n=== RECETA CLONADA Y MODIFICADA ===");
        Recipe largePizza = pizzaRecipe.cloneWithModifications("Pizza Margherita Familiar", 8);
        logger.info(largePizza.toString());

        logger.info("\n=== OTRA VARIANTE ===");
        Recipe personalPizza = pizzaRecipe.cloneWithModifications("Pizza Personal", 1);
        logger.info(personalPizza.toString());

        printPatternFooter();
    }

    private static void demonstrateSingleton() {
        printPatternHeader("SINGLETON", "Creacional");
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

    private static void demonstrateAdapter() {
        printPatternHeader("ADAPTER", "Estructural");
        logger.info("Permite que interfaces incompatibles trabajen juntas.");
        logger.info("Contexto: Adaptar sistema legacy de pagos a interfaz moderna.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- LegacyPOSSystem tiene métodos validateCard() y chargeCard()");
        logger.info("- ModernPaymentProcessor espera validatePaymentMethod() y processPayment()");
        logger.info("- PaymentAdapter implementa ModernPaymentProcessor");
        logger.info("- Internamente convierte las llamadas al formato del sistema legacy\n");

        LegacyPOSSystem legacySystem = new LegacyPOSSystem();
        ModernPaymentProcessor processor = new PaymentAdapter(legacySystem);

        PaymentRequest request = new PaymentRequest("4532123456789012", 125.50, "Juan Pérez");

        logger.info("=== Validando método de pago ===");
        boolean isValid = processor.validatePaymentMethod(request);
        logger.info("Válido: {}", isValid);

        logger.info("\n=== Procesando pago ===");
        PaymentResult result = processor.processPayment(request);
        logger.info(result.toString());

        printPatternFooter();
    }

    private static void demonstrateBridge() {
        printPatternHeader("BRIDGE", "Estructural");
        logger.info("Separa abstracción de implementación.");
        logger.info("Contexto: Notificaciones del restaurante con diferentes métodos de envío.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Abstracción: Notification (tipos: Order, Reservation, Promotion)");
        logger.info("- Implementación: NotificationSender (Email, SMS, Push)");
        logger.info("- Ambas jerarquías evolucionan independientemente");
        logger.info("- Puedes combinar cualquier tipo de notificación con cualquier canal\n");

        // Crear diferentes implementaciones
        NotificationSender email = new EmailSender();
        NotificationSender sms = new SMSSender();
        NotificationSender push = new PushNotificationSender();

        // Crear notificaciones con diferentes implementaciones
        logger.info("=== CONFIRMACIÓN DE ORDEN ===");
        Notification orderConfirmation = new OrderConfirmationNotification(email, "ORD-1001", 45.99);
        orderConfirmation.notify("cliente@email.com");

        logger.info("=== RESERVACIÓN ===");
        Notification reservation = new ReservationNotification(sms, "2024-12-25", "19:00", 5);
        reservation.notify("+1234567890");

        logger.info("=== PROMOCIÓN ===");
        Notification promotion = new PromotionNotification(push, "2x1 en Pizzas", 50);
        promotion.notify("device-token-123");

        logger.info("\n=== Cambiando método de envío dinámicamente ===");
        orderConfirmation.changeSender(sms);
        orderConfirmation.notify("+0987654321");

        printPatternFooter();
    }

    private static void demonstrateComposite() {
        printPatternHeader("COMPOSITE", "Estructural");
        logger.info("Compone objetos en estructuras de árbol.");
        logger.info("Contexto: Estructura jerárquica del menú del restaurante.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- MenuComponent es la interfaz común para hojas y compuestos");
        logger.info("- MenuItem (hoja) representa un plato individual");
        logger.info("- MenuCategory (compuesto) contiene MenuItems u otras categorías");
        logger.info("- El cliente trata uniformemente hojas y compuestos\n");

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

        logger.info("\n=== Información del menú ===");
        String totalPrice = String.format("%.2f", mainMenu.getPrice());
        logger.info("Precio total del menú completo: ${}", totalPrice);

        printPatternFooter();
    }

    private static void demonstrateDecorator() {
        printPatternHeader("DECORATOR", "Estructural");
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

        String separator50 = "─".repeat(50);
        logger.info("\n{}\n", separator50);

        // Bebida compleja
        Beverage beverage2 = new Coffee();
        beverage2 = new MilkDecorator(beverage2);
        beverage2 = new WhippedCreamDecorator(beverage2);
        beverage2 = new CaramelDecorator(beverage2);
        beverage2 = new SugarDecorator(beverage2);
        logger.info(beverage2.getDescription().toString());
        double finalPrice = beverage2.getCost();
        logger.info("Precio final: ${}", String.format("%.2f", finalPrice));

        logger.info("\n{}\n", separator50);

        // Té con miel (azúcar)
        Beverage tea = new Tea();
        tea = new SugarDecorator(tea);
        logger.info("{} - ${}", tea.getDescription(), tea.getCost());

        printPatternFooter();
    }

    private static void demonstrateFacade() {
        printPatternHeader("FACADE", "Estructural");
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

    private static void demonstrateFlyweight() {
        printPatternHeader("FLYWEIGHT", "Estructural");
        logger.info("Comparte objetos para soportar grandes cantidades eficientemente.");
        logger.info("Contexto: Optimización de memoria para tipos de mesas.\n");

        logger.info("Creando 20 mesas en el restaurante...\n");

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
        logger.info("\n=== Mesas seleccionadas ===");
        tables[0].displayInfo();
        tables[5].displayInfo();
        tables[10].displayInfo();
        tables[15].displayInfo();

        TableTypeFactory.printStatistics();

        printPatternFooter();
    }

    private static void demonstrateProxy() {
        printPatternHeader("PROXY", "Estructural");
        logger.info("Proporciona sustituto o marcador de posición para otro objeto.");
        logger.info("Contexto: Carga lazy de imágenes del menú y control de acceso.\n");

        logger.info("=== VIRTUAL PROXY (Lazy Loading) ===");
        MenuImage image1 = new MenuImageProxy("menu_especial.jpg");
        logger.info("Proxy creado, imagen NO cargada todavía\n");

        logger.info("Primera visualización:");
        image1.display();

        logger.info("\nSegunda visualización (imagen ya en memoria):");
        image1.display();

        String separator50Proxy = "─".repeat(50);
        logger.info("\n{}\n", separator50Proxy);

        logger.info("=== PROTECTION PROXY (Control de Acceso) ===");
        MenuImage confidentialMenu1 = new ProtectedMenuImage("menu_costos.jpg", "ADMIN");
        confidentialMenu1.display();

        logger.info("");
        MenuImage confidentialMenu2 = new ProtectedMenuImage("menu_costos.jpg", "WAITER");
        confidentialMenu2.display();

        printPatternFooter();
    }

    // ==================== PATRONES DE COMPORTAMIENTO ====================

    private static void demonstrateChainOfResponsibility() {
        printPatternHeader("CHAIN OF RESPONSIBILITY", "Comportamiento");
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
        printPatternHeader("COMMAND", "Comportamiento");
        logger.info("Encapsula solicitudes como objetos.");
        logger.info("Contexto: Sistema de órdenes con soporte de undo.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- Command es la interfaz con execute() y undo()");
        logger.info("- Comandos concretos: AddItemCommand, RemoveItemCommand");
        logger.info("- KitchenOrder (Invoker) ejecuta y almacena comandos");
        logger.info("- Permite deshacer operaciones con executeUndo()\n");

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

    private static void demonstrateIterator() {
        printPatternHeader("ITERATOR", "Comportamiento");
        logger.info("Accede secuencialmente a elementos de una colección.");
        logger.info("Contexto: Iteración sobre platos especiales del día.\n");

        DailyMenuCollection menu = new DailyMenuCollection();
        menu.addSpecial(new DailySpecial("Tacos al Pastor", "Mexicana", 12.99, "Martes"));
        menu.addSpecial(new DailySpecial("Sushi Variado", "Japonesa", 18.99, "Miércoles"));
        menu.addSpecial(new DailySpecial("Paella", "Española", 22.99, "Jueves"));
        menu.addSpecial(new DailySpecial("Pizza Napolitana", "Italiana", 14.99, "Viernes"));
        menu.addSpecial(new DailySpecial("Pad Thai", "Tailandesa", 13.99, "Sábado"));

        logger.info("=== TODOS LOS ESPECIALES ===");
        MenuIterator iterator = menu.createIterator();
        while (iterator.hasNext()) {
            logger.info(iterator.next().toString());
        }

        logger.info("\n=== SOLO COMIDA ITALIANA ===");
        MenuIterator italianIterator = menu.createFilteredIterator("Italiana");
        while (italianIterator.hasNext()) {
            logger.info(italianIterator.next().toString());
        }

        printPatternFooter();
    }

    private static void demonstrateMediator() {
        printPatternHeader("MEDIATOR", "Comportamiento");
        logger.info("Reduce dependencias entre objetos mediante un mediador.");
        logger.info("Contexto: Coordinación entre departamentos del restaurante.\n");

        RestaurantCoordinator mediator = new RestaurantCoordinator();

        FrontDesk frontDesk = new FrontDesk(mediator);
        Kitchen kitchen = new Kitchen(mediator);
        DeliveryService delivery = new DeliveryService(mediator);

        mediator.registerComponent(frontDesk);
        mediator.registerComponent(kitchen);
        mediator.registerComponent(delivery);

        logger.info("\n=== Flujo de una orden ===");
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
        logger.info("Captura y restaura el estado interno de un objeto.");
        logger.info("Contexto: Historial de cambios en una orden.\n");

        com.example.patterns.behavioral.memento.Order order = 
            new com.example.patterns.behavioral.memento.Order("ORD-4001");
        OrderHistory history = new OrderHistory();

        logger.info("=== Estado inicial ===");
        logger.info(order.toString());
        history.save(order);

        logger.info("\n=== Agregando items ===");
        order.addItem("Pizza", 15.99);
        logger.info(order.toString());
        history.save(order);

        order.addItem("Refresco", 2.99);
        logger.info(order.toString());
        history.save(order);

        order.setStatus("CONFIRMADA");
        logger.info(order.toString());
        history.save(order);

        logger.info("\n=== Cliente quiere deshacer cambios ===");
        history.undo(order);
        logger.info(order.toString());

        history.undo(order);
        logger.info(order.toString());

        printPatternFooter();
    }

    private static void demonstrateObserver() {
        printPatternHeader("OBSERVER", "Comportamiento");
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
        logger.info("Cambia comportamiento según el estado interno.");
        logger.info("Contexto: Estados de una mesa del restaurante.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- TableState es la interfaz de estado con métodos de transición");
        logger.info("- Estados concretos: AvailableState, ReservedState, OccupiedState, DirtyState");
        logger.info("- TableContext delega operaciones al estado actual");
        logger.info("- Cada estado define qué transiciones son válidas\n");

        TableContext table = new TableContext(10);

        logger.info("\n=== Flujo normal de una mesa ===");
        table.reserve();
        logger.info("Estado actual: {}", table.getCurrentState());

        logger.info("");
        table.occupy();
        logger.info("Estado actual: {}", table.getCurrentState());

        logger.info("");
        table.free();
        logger.info("Estado actual: {}", table.getCurrentState());

        logger.info("");
        table.clean();
        logger.info("Estado actual: {}", table.getCurrentState());

        logger.info("\n=== Intentando acciones inválidas ===");
        table.clean(); // Ya está limpia
        table.occupy(); // Ocupar directamente
        table.reserve(); // No se puede reservar ocupada

        printPatternFooter();
    }

    private static void demonstrateStrategy() {
        printPatternHeader("STRATEGY", "Comportamiento");
        logger.info("Define familia de algoritmos intercambiables.");
        logger.info("Contexto: Diferentes estrategias de precios.\n");
        
        logger.info(">>> CÓMO SE APLICA:");
        logger.info("- PricingStrategy es la interfaz de estrategia");
        logger.info("- Estrategias concretas: Regular, HappyHour, Weekend, Member");
        logger.info("- MenuItem contiene una referencia a PricingStrategy");
        logger.info("- Se puede cambiar la estrategia en tiempo de ejecución\n");

        com.example.patterns.behavioral.strategy.MenuItem item = 
            new com.example.patterns.behavioral.strategy.MenuItem("Hamburguesa Deluxe", 15.00);

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

    private static void demonstrateTemplateMethod() {
        printPatternHeader("TEMPLATE METHOD", "Comportamiento");
        logger.info("Define esqueleto de algoritmo, delegando pasos a subclases.");
        logger.info("Contexto: Proceso de preparación de diferentes comidas.\n");

        logger.info("=== Preparando Hamburguesa ===");
        MealPreparation burger = new BurgerPreparation();
        burger.prepareMeal();

        logger.info("\n=== Preparando Ensalada ===");
        MealPreparation salad = new SaladPreparation();
        salad.prepareMeal();

        logger.info("\n=== Preparando Pasta (con salsa) ===");
        MealPreparation pastaWithSauce = new PastaPreparation(true);
        pastaWithSauce.prepareMeal();

        printPatternFooter();
    }

    private static void demonstrateVisitor() {
        printPatternHeader("VISITOR", "Comportamiento");
        logger.info("Separa algoritmos de objetos sobre los que operan.");
        logger.info("Contexto: Operaciones sobre items del menú.\n");

        // Crear items del menú
        VisitableMenuItem[] order = {
            new Appetizer("Nachos", 8.99, true),
            new MainCourse("Filete de res", 24.99, 650),
            new Dessert("Tiramisú", 6.99, 45),
            new Drink("Limonada", 3.99, 350)
        };

        // Visitor para calcular precio
        logger.info("=== CÁLCULO DE PRECIO ===");
        PriceCalculatorVisitor priceCalculator = new PriceCalculatorVisitor();
        for (VisitableMenuItem item : order) {
            item.accept(priceCalculator);
        }
        String totalPriceFormatted = String.format("%.2f", priceCalculator.getTotalPrice());
        logger.info("\nPrecio total: ${}", totalPriceFormatted);

        String separatorVisitor = "─".repeat(50);
        logger.info("\n{}\n", separatorVisitor);

        // Visitor para análisis nutricional
        logger.info("=== ANÁLISIS NUTRICIONAL ===");
        NutritionalAnalysisVisitor nutritionAnalysis = new NutritionalAnalysisVisitor();
        for (VisitableMenuItem item : order) {
            item.accept(nutritionAnalysis);
        }
        logger.info("\nTotal calorías: {} kcal", nutritionAnalysis.getTotalCalories());
        logger.info("Total azúcar: {}g", nutritionAnalysis.getTotalSugar());

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
        String separator = "─".repeat(60);
        logger.info("\n{}", separator);
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




