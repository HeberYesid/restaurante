package com.example.patterns.behavioral.chainofresponsibility;

/**
 * Patrón Chain of Responsibility
 * Representa una solicitud de descuento
 */
public class DiscountRequest {
    private String customerType;
    private double orderAmount;
    private boolean isLoyalCustomer;
    private int visitCount;

    public DiscountRequest(String customerType, double orderAmount, boolean isLoyalCustomer, int visitCount) {
        this.customerType = customerType;
        this.orderAmount = orderAmount;
        this.isLoyalCustomer = isLoyalCustomer;
        this.visitCount = visitCount;
    }

    public String getCustomerType() {
        return customerType;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public boolean isLoyalCustomer() {
        return isLoyalCustomer;
    }

    public int getVisitCount() {
        return visitCount;
    }
}
