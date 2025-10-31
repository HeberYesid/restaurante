package com.example.patterns.structural.flyweight;

/**
 * Estado extrínseco (único para cada instancia)
 */
public class Table {
    private final int number;
    private boolean isOccupied;
    private final TableType tableType; // Flyweight compartido

    public Table(int number, TableType tableType) {
        this.number = number;
        this.tableType = tableType;
        this.isOccupied = false;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void free() {
        this.isOccupied = false;
    }

    public void displayInfo() {
        tableType.displayInfo(number, isOccupied);
    }

    public int getNumber() {
        return number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public TableType getTableType() {
        return tableType;
    }
}
