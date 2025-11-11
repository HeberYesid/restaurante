package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservedState implements TableState {
    private static final Logger logger = LoggerFactory.getLogger(ReservedState.class);
    
    @Override
    public void reserve(TableContext table) {
        logger.info("La mesa ya está reservada");
    }

    @Override
    public void occupy(TableContext table) {
        logger.info("Cliente llegó. Mesa {} ahora ocupada", table.getTableNumber());
        table.setState(new OccupiedState());
    }

    @Override
    public void free(TableContext table) {
        logger.info("Reservación cancelada. Mesa {} disponible", table.getTableNumber());
        table.setState(new AvailableState());
    }

    @Override
    public void clean(TableContext table) {
        logger.info("No se puede limpiar una mesa reservada");
    }

    @Override
    public String getStateName() {
        return "RESERVADA";
    }
}
