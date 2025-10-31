package com.example.patterns.behavioral.chainofresponsibility;

/**
 * Handler abstracto
 */
public abstract class DiscountHandler {
    protected DiscountHandler nextHandler;

    public void setNext(DiscountHandler handler) {
        this.nextHandler = handler;
    }

    public abstract double handleDiscount(DiscountRequest request);
}
