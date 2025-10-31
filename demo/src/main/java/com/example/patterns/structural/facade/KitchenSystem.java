package com.example.patterns.structural.facade;

public class KitchenSystem {
    public void sendOrderToKitchen(String orderDetails) {
        System.out.println("Enviando orden a cocina: " + orderDetails);
    }

    public void notifyOrderReady(String orderId) {
        System.out.println("Orden " + orderId + " lista para servir");
    }
}
