package com.example.patterns.behavioral.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDiscountHandler extends DiscountHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDiscountHandler.class);
    
    @Override
    public double handleDiscount(DiscountRequest request) {
        logger.info("DefaultDiscountHandler: Aplicando descuento estándar del 5%");
        return 0.05;
    }
}
