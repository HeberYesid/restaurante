package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoyaltyDiscountHandler extends DiscountHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoyaltyDiscountHandler.class);
    
    @Override
    public double handleDiscount(DiscountRequest request) {
        if (request.isLoyalCustomer() && request.getVisitCount() >= 10) {
            logger.info("LoyaltyDiscountHandler: Aplicando descuento por lealtad del 15%");
            return 0.15;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
