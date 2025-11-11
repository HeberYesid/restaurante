package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BurgerPreparation extends MealPreparation {
    private static final Logger logger = LoggerFactory.getLogger(BurgerPreparation.class);
    
    @Override
    protected void gatherIngredients() {
        logger.info("Reuniendo: pan, carne, lechuga, tomate, queso");
    }

    @Override
    protected void prepareIngredients() {
        logger.info("Cortando vegetales, formando hamburguesa");
    }

    @Override
    protected void cook() {
        logger.info("Asando hamburguesa a la parrilla por 5 minutos cada lado");
    }

    @Override
    protected void addCondiments() {
        logger.info("Agregando ketchup, mostaza y mayonesa");
    }

    @Override
    protected void serve() {
        logger.info("Sirviendo hamburguesa con papas fritas");
    }
}
