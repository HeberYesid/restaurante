package com.example.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Patrón Facade
 * Proporciona una interfaz simplificada para el sistema complejo de restaurante
 */
public class RestaurantFacade {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantFacade.class);
    private InventorySystem inventory;
    private PaymentSystem payment;
    private KitchenSystem kitchen;
    private DeliverySystem delivery;

    public RestaurantFacade() {
        this.inventory = new InventorySystem();
        this.payment = new PaymentSystem();
        this.kitchen = new KitchenSystem();
        this.delivery = new DeliverySystem();
    }

    public boolean placeOrder(String orderId, String items, double total, String paymentMethod) {
        logger.info("\n=== Procesando orden {} ===", orderId);
        
        // Verificar inventario
        if (!inventory.checkAvailability(items)) {
            logger.error("ERROR: Artículos no disponibles");
            return false;
        }

        // Procesar pago
        if (!payment.processPayment(total, paymentMethod)) {
            logger.error("ERROR: Pago rechazado");
            return false;
        }

        // Actualizar inventario
        inventory.updateStock(items, 1);

        // Enviar a cocina
        kitchen.sendOrderToKitchen(items);

        // Generar recibo
        payment.generateReceipt(orderId, total);

        logger.info("Orden procesada exitosamente\n");
        return true;
    }

    public void placeDeliveryOrder(String orderId, String items, double total, 
                                   String paymentMethod, String address) {
        logger.info("\n=== Procesando orden de entrega {} ===", orderId);
        
        if (placeOrder(orderId, items, total, paymentMethod)) {
            delivery.scheduleDelivery(orderId, address);
            delivery.assignDriver(orderId);
            logger.info("Orden de entrega procesada exitosamente\n");
        }
    }

    public void notifyOrderComplete(String orderId) {
        kitchen.notifyOrderReady(orderId);
    }
}
