package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BulkOrderDiscountHandler extends DiscountHandler {
    private static final Logger logger = LoggerFactory.getLogger(BulkOrderDiscountHandler.class);
    
    @Override
    public double handleDiscount(DiscountRequest request) {
        if (request.getOrderAmount() > 100.0) {
            logger.info("BulkOrderDiscountHandler: Aplicando descuento por compra mayor del 10%");
            return 0.10;
        } else if (nextHandler != null) {
            return nextHandler.handleDiscount(request);
        }
        return 0.0;
    }
}
