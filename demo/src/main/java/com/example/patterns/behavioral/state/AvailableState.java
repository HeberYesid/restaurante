package com.example.patterns.behavioral.state;

public class AvailableState implements TableState {
    @Override
    public void reserve(TableContext table) {
        System.out.println("Mesa " + table.getTableNumber() + " reservada");
        table.setState(new ReservedState());
    }

    @Override
    public void occupy(TableContext table) {
        System.out.println("Mesa " + table.getTableNumber() + " ocupada directamente");
        table.setState(new OccupiedState());
    }

    @Override
    public void free(TableContext table) {
        System.out.println("La mesa ya está disponible");
    }

    @Override
    public void clean(TableContext table) {
        System.out.println("La mesa ya está limpia y disponible");
    }

    @Override
    public String getStateName() {
        return "DISPONIBLE";
    }
}
