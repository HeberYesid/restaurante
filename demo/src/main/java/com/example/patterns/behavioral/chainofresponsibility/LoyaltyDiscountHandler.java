package com.example.patterns.behavioral.chainofresponsibility;

public class LoyaltyDiscountHandler extends DiscountHandler {
    @Override
    public double handleDiscount(DiscountRequest request) {
        if (request.isLoyalCustomer() && request.getVisitCount() >= 10) {
            System.out.println("LoyaltyDiscountHandler: Aplicando descuento por lealtad del 15%");
            return 0.15;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
