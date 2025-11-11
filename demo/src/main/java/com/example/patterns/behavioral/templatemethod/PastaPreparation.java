package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PastaPreparation extends MealPreparation {
    private static final Logger logger = LoggerFactory.getLogger(PastaPreparation.class);
    private boolean withSauce;

    public PastaPreparation(boolean withSauce) {
        this.withSauce = withSauce;
    }

    @Override
    protected void gatherIngredients() {
        logger.info("Reuniendo: pasta, agua, sal, aceite");
        if (withSauce) {
            logger.info("También reuniendo ingredientes para salsa");
        }
    }

    @Override
    protected void prepareIngredients() {
        logger.info("Midiendo porciones de pasta");
    }

    @Override
    protected void cook() {
        logger.info("Hirviendo agua y cocinando pasta por 8-10 minutos");
        if (withSauce) {
            logger.info("Preparando salsa simultáneamente");
        }
    }

    @Override
    protected boolean customerWantsCondiments() {
        return withSauce;
    }

    @Override
    protected void addCondiments() {
        logger.info("Agregando salsa y queso parmesano");
    }

    @Override
    protected void serve() {
        logger.info("Sirviendo pasta caliente en plato hondo");
    }
}
