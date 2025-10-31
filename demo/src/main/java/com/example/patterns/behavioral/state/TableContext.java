package com.example.patterns.behavioral.state;

/**
 * Context
 */
public class TableContext {
    private TableState state;
    private int tableNumber;

    public TableContext(int tableNumber) {
        this.tableNumber = tableNumber;
        this.state = new AvailableState();
        System.out.println("Mesa " + tableNumber + " creada en estado: " + state.getStateName());
    }

    public void setState(TableState state) {
        System.out.println("Mesa " + tableNumber + " cambia de " + 
                         this.state.getStateName() + " a " + state.getStateName());
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
