package com.example.patterns.behavioral.observer;

public class KitchenDisplay implements OrderObserver {
    private int displayNumber;

    public KitchenDisplay(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    @Override
    public void update(String orderId, String status, String message) {
        System.out.println("[PANTALLA COCINA #" + displayNumber + "]");
        System.out.println("  >>> Orden " + orderId + ": " + status);
        if (status.equals("CONFIRMADA")) {
            System.out.println("  >>> Iniciar preparación");
        }
    }

    @Override
    public String getObserverName() {
        return "Pantalla Cocina #" + displayNumber;
    }
}
