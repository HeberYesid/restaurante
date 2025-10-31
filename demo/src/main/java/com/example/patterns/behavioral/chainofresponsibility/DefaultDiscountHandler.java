package com.example.patterns.behavioral.chainofresponsibility;

public class DefaultDiscountHandler extends DiscountHandler {
    @Override
    public double handleDiscount(DiscountRequest request) {
        System.out.println("DefaultDiscountHandler: Aplicando descuento estándar del 5%");
        return 0.05;
    }
}
