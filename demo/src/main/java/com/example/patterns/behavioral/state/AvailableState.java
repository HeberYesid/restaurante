package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvailableState implements TableState {
    private static final Logger logger = LoggerFactory.getLogger(AvailableState.class);
    
    @Override
    public void reserve(TableContext table) {
        logger.info("Mesa {} reservada", table.getTableNumber());
        table.setState(new ReservedState());
    }

    @Override
    public void occupy(TableContext table) {
        logger.info("Mesa {} ocupada directamente", table.getTableNumber());
        table.setState(new OccupiedState());
    }

    @Override
    public void free(TableContext table) {
        logger.info("La mesa ya está disponible");
    }

    @Override
    public void clean(TableContext table) {
        logger.info("La mesa ya está limpia y disponible");
    }

    @Override
    public String getStateName() {
        return "DISPONIBLE";
    }
}
