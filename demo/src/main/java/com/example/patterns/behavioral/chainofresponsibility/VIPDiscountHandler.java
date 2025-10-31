package com.example.patterns.behavioral.chainofresponsibility;

public class VIPDiscountHandler extends DiscountHandler {
    @Override
    public double handleDiscount(DiscountRequest request) {
        if ("VIP".equals(request.getCustomerType())) {
            System.out.println("VIPDiscountHandler: Aplicando descuento VIP del 25%");
            return 0.25;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
