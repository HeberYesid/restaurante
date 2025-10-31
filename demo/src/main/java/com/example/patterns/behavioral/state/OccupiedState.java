package com.example.patterns.behavioral.state;

public class OccupiedState implements TableState {
    @Override
    public void reserve(TableContext table) {
        System.out.println("No se puede reservar una mesa ocupada");
    }

    @Override
    public void occupy(TableContext table) {
        System.out.println("La mesa ya está ocupada");
    }

    @Override
    public void free(TableContext table) {
        System.out.println("Clientes se fueron. Mesa " + table.getTableNumber() + " necesita limpieza");
        table.setState(new DirtyState());
    }

    @Override
    public void clean(TableContext table) {
        System.out.println("No se puede limpiar mientras está ocupada");
    }

    @Override
    public String getStateName() {
        return "OCUPADA";
    }
}
