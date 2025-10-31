package com.example.patterns.behavioral.state;

public class ReservedState implements TableState {
    @Override
    public void reserve(TableContext table) {
        System.out.println("La mesa ya está reservada");
    }

    @Override
    public void occupy(TableContext table) {
        System.out.println("Cliente llegó. Mesa " + table.getTableNumber() + " ahora ocupada");
        table.setState(new OccupiedState());
    }

    @Override
    public void free(TableContext table) {
        System.out.println("Reservación cancelada. Mesa " + table.getTableNumber() + " disponible");
        table.setState(new AvailableState());
    }

    @Override
    public void clean(TableContext table) {
        System.out.println("No se puede limpiar una mesa reservada");
    }

    @Override
    public String getStateName() {
        return "RESERVADA";
    }
}
