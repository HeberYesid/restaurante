package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirtyState implements TableState {
    private static final Logger logger = LoggerFactory.getLogger(DirtyState.class);
    
    @Override
    public void reserve(TableContext table) {
        logger.info("No se puede reservar una mesa sucia");
    }

    @Override
    public void occupy(TableContext table) {
        logger.info("No se puede ocupar una mesa sucia");
    }

    @Override
    public void free(TableContext table) {
        logger.info("La mesa ya está libre pero necesita limpieza");
    }

    @Override
    public void clean(TableContext table) {
        logger.info("Limpiando mesa {}...", table.getTableNumber());
        table.setState(new AvailableState());
    }

    @Override
    public String getStateName() {
        return "SUCIA";
    }
}
