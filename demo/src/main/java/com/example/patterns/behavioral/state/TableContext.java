package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Context
 */
public class TableContext {
    private static final Logger logger = LoggerFactory.getLogger(TableContext.class);
    private TableState state;
    private int tableNumber;

    public TableContext(int tableNumber) {
        this.tableNumber = tableNumber;
        this.state = new AvailableState();
        logger.info("Mesa {} creada en estado: {}", tableNumber, state.getStateName());
    }

    public void setState(TableState state) {
        logger.info("Mesa {} cambia de {} a {}", tableNumber, 
                         this.state.getStateName(), state.getStateName());
        this.state = state;
    }

    public void reserve() {
        state.reserve(this);
    }

    public void occupy() {
        state.occupy(this);
    }

    public void free() {
        state.free(this);
    }

    public void clean() {
        state.clean(this);
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getCurrentState() {
        return state.getStateName();
    }
}
