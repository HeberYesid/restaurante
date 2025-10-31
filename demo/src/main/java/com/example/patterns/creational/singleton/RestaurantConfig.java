package com.example.patterns.creational.singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Patrón Singleton - Thread-Safe
 * Sistema de configuración del restaurante
 */
public class RestaurantConfig {
    private static volatile RestaurantConfig instance;
    private static final Object lock = new Object();
    
    private String restaurantName;
    private String address;
    private int maxTables;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private List<String> paymentMethods;
    private double taxRate;
    private AtomicInteger orderCounter;

    private RestaurantConfig() {
        // Configuración por defecto
        this.restaurantName = "La Cocina del Patrón";
        this.address = "Av. Principal 123";
        this.maxTables = 20;
        this.openingTime = LocalDateTime.now().withHour(11).withMinute(0);
        this.closingTime = LocalDateTime.now().withHour(23).withMinute(0);
        this.paymentMethods = new ArrayList<>();
        this.paymentMethods.add("Efectivo");
        this.paymentMethods.add("Tarjeta de Crédito");
        this.paymentMethods.add("Tarjeta de Débito");
        this.taxRate = 0.16; // 16% IVA
        this.orderCounter = new AtomicInteger(1000);
    }

    // Double-checked locking
    public static RestaurantConfig getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RestaurantConfig();
                }
            }
        }
        return instance;
    }

    public int getNextOrderNumber() {
        return orderCounter.incrementAndGet();
    }

    // Getters y Setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxTables() {
        return maxTables;
    }

    public void setMaxTables(int maxTables) {
        this.maxTables = maxTables;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public List<String> getPaymentMethods() {
        return new ArrayList<>(paymentMethods);
    }

    public void addPaymentMethod(String method) {
        if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
        }
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double calculateTax(double amount) {
        return amount * taxRate;
    }

    public double calculateTotal(double subtotal) {
        return subtotal + calculateTax(subtotal);
    }

    @Override
    public String toString() {
        return String.format(
            "===== CONFIGURACIÓN DEL RESTAURANTE =====\n" +
            "Nombre: %s\n" +
            "Dirección: %s\n" +
            "Mesas disponibles: %d\n" +
            "Horario: %s - %s\n" +
            "Métodos de pago: %s\n" +
            "Tasa de impuesto: %.2f%%\n" +
            "Próximo número de orden: %d",
            restaurantName, address, maxTables,
            openingTime.toLocalTime(), closingTime.toLocalTime(),
            String.join(", ", paymentMethods),
            taxRate * 100,
            orderCounter.get() + 1
        );
    }

    // Prevenir clonación
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone singleton");
    }
}
