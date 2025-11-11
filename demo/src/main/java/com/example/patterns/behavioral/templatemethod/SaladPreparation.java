package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaladPreparation extends MealPreparation {
    private static final Logger logger = LoggerFactory.getLogger(SaladPreparation.class);
    
    @Override
    protected void gatherIngredients() {
        logger.info("Reuniendo: lechuga, tomate, pepino, cebolla, aceitunas");
    }

    @Override
    protected void prepareIngredients() {
        logger.info("Lavando y cortando todos los vegetales");
    }

    @Override
    protected void cook() {
        logger.info("No requiere cocción (ensalada fresca)");
    }

    @Override
    protected void addCondiments() {
        logger.info("Agregando vinagreta y aceite de oliva");
    }

    @Override
    protected void serve() {
        logger.info("Sirviendo ensalada en bowl grande");
    }
}
