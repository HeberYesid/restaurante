package com.example.patterns.behavioral.chainofresponsibility;

public class BulkOrderDiscountHandler extends DiscountHandler {
    @Override
    public double handleDiscount(DiscountRequest request) {
        if (request.getOrderAmount() > 100.0) {
            System.out.println("BulkOrderDiscountHandler: Aplicando descuento por compra mayor del 10%");
            return 0.10;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
