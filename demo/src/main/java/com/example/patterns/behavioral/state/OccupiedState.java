package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OccupiedState implements TableState {
    private static final Logger logger = LoggerFactory.getLogger(OccupiedState.class);
    
    @Override
    public void reserve(TableContext table) {
        logger.info("No se puede reservar una mesa ocupada");
    }

    @Override
    public void occupy(TableContext table) {
        logger.info("La mesa ya está ocupada");
    }

    @Override
    public void free(TableContext table) {
        logger.info("Clientes se fueron. Mesa {} necesita limpieza", table.getTableNumber());
        table.setState(new DirtyState());
    }

    @Override
    public void clean(TableContext table) {
        logger.info("No se puede limpiar mientras está ocupada");
    }

    @Override
    public String getStateName() {
        return "OCUPADA";
    }
}
