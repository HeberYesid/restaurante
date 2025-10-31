package com.example.patterns.structural.facade;

public class PaymentSystem {
    public boolean processPayment(double amount, String method) {
        System.out.println("Procesando pago de $" + amount + " mediante " + method);
        return true;
    }

    public void generateReceipt(String orderId, double amount) {
        System.out.println("Generando recibo para orden " + orderId + " por $" + amount);
    }
}
