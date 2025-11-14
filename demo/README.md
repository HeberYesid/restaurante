# Sistema de Restaurante - Patrones de Diseño GoF

Aplicación Java que demuestra 9 patrones de diseño en el contexto de un sistema de gestión de restaurante.

## 🏗️ Patrones Implementados

| Patrón | Descripción | Cobertura |
|--------|-------------|-----------|
| **Factory Method** | Creación de diferentes tipos de platos | 85% |
| **Builder** | Construcción fluida de menús | 91% |
| **Singleton** | Configuración global del restaurante | 100% |
| **Decorator** | Personalización de bebidas con extras | 91% |
| **Facade** | Simplificación de subsistemas complejos | 94% |
| **Chain of Responsibility** | Sistema de descuentos encadenados | 96% |
| **Command** | Órdenes con undo/redo | 100% |
| **Observer** | Notificación de cambios de estado | 100% |
| **Strategy** | Estrategias dinámicas de precios | 100% |

## 📊 Cobertura de Código

- **Total**: 58% (95.1% sin Main.java)
- **Tests**: 228 (100% pasando)
- **Tiempo**: ~13.8 segundos
- **4 patrones al 100%**: Command, Strategy, Observer, Singleton

## 🚀 Inicio Rápido

### Compilar
```bash
mvn clean compile
```

### Ejecutar Tests
```bash
mvn clean test
```

### Generar Reporte de Cobertura
```bash
mvn clean test jacoco:report
```
Abre `target/site/jacoco/index.html` para ver el reporte detallado.

### Ejecutar Aplicación
```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

## 📁 Estructura del Proyecto

```
src/main/java/com/example/
├── patterns/
│   ├── creational/
│   │   ├── factory/        (Factory Method)
│   │   ├── builder/        (Builder)
│   │   └── singleton/      (Singleton)
│   ├── structural/
│   │   ├── decorator/      (Decorator)
│   │   └── facade/         (Facade)
│   └── behavioral/
│       ├── command/        (Command)
│       ├── observer/       (Observer)
│       ├── strategy/       (Strategy)
│       └── chainofresponsibility/ (Chain)
└── Main.java               (Aplicación interactiva)

src/test/java/com/example/
├── patterns/               (228 tests unitarios)
├── integration/            (11 tests de integración)
└── e2e/                    (17 tests end-to-end)
```

## 🧪 Pruebas

- **228 tests totales** organizados en 10 suites
- Cobertura de instrucciones: 1,682/2,872 (58%)
- Todas las suites de patrones con tests exhaustivos
- Tests de integración combinando múltiples patrones

## 🔧 Requisitos

- Java 17+
- Maven 3.6+

## 📝 Ejemplos de Uso

### Factory Method
```java
Restaurant italian = new ItalianRestaurant();
italian.orderDish();  // Crea Pizza
```

### Builder
```java
Menu menu = new Menu.Builder()
    .name("Mi Menú")
    .addMainCourse("Pasta", 12.99)
    .addDessert("Tiramisu", 7.50)
    .build();
```

### Observer
```java
OrderTracker tracker = new OrderTracker("ORD-001");
tracker.attach(new CustomerNotifier("Juan"));
tracker.updateStatus("CONFIRMADA", "Iniciando preparación");
```

### Strategy
```java
MenuItem item = new MenuItem("Hamburguesa", 15.00);
item.setPricingStrategy(new HappyHourPricing());
```

## 📈 Estado del Proyecto

✅ Todos los tests pasando  
✅ Cobertura >95% (excluyendo Main.java)  
✅ Sin errores de compilación  
✅ Código limpio y bien documentado  

## 📄 Licencia

Proyecto educativo - Demostración de Patrones de Diseño GoF
