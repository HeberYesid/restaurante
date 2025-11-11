package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VIPDiscountHandler extends DiscountHandler {
    private static final Logger logger = LoggerFactory.getLogger(VIPDiscountHandler.class);
    
    @Override
    public double handleDiscount(DiscountRequest request) {
        if ("VIP".equals(request.getCustomerType())) {
            logger.info("VIPDiscountHandler: Aplicando descuento VIP del 25%");
            return 0.25;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
