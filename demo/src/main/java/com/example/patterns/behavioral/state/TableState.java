package com.example.patterns.behavioral.state;

/**
 * Patrón State - Interfaz de estado
 */
public interface TableState {
    void reserve(TableContext table);
    void occupy(TableContext table);
    void free(TableContext table);
    void clean(TableContext table);
    String getStateName();
}
