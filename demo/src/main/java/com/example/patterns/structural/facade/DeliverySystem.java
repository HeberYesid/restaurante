package com.example.patterns.structural.facade;

public class DeliverySystem {
    public void scheduleDelivery(String orderId, String address) {
        System.out.println("Programando entrega para orden " + orderId + " a " + address);
    }

    public void assignDriver(String orderId) {
        System.out.println("Asignando conductor para orden " + orderId);
    }
}
