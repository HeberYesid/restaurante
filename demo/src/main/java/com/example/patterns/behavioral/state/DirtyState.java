package com.example.patterns.behavioral.state;

public class DirtyState implements TableState {
    @Override
    public void reserve(TableContext table) {
        System.out.println("No se puede reservar una mesa sucia");
    }

    @Override
    public void occupy(TableContext table) {
        System.out.println("No se puede ocupar una mesa sucia");
    }

    @Override
    public void free(TableContext table) {
        System.out.println("La mesa ya está libre pero necesita limpieza");
    }

    @Override
    public void clean(TableContext table) {
        System.out.println("Limpiando mesa " + table.getTableNumber() + "...");
        table.setState(new AvailableState());
    }

    @Override
    public String getStateName() {
        return "SUCIA";
    }
}
