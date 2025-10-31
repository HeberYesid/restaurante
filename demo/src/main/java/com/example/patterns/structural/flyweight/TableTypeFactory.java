package com.example.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Fábrica de Flyweight
 */
public class TableTypeFactory {
    private static final Map<String, TableType> tableTypes = new HashMap<>();

    public static TableType getTableType(String type, int capacity, String location, boolean hasWindowView) {
        String key = type + "_" + capacity + "_" + location + "_" + hasWindowView;
        
        TableType tableType = tableTypes.get(key);
        
        if (tableType == null) {
            tableType = new TableType(type, capacity, location, hasWindowView);
            tableTypes.put(key, tableType);
        }
        
        return tableType;
    }

    public static int getTotalTableTypes() {
        return tableTypes.size();
    }

    public static void printStatistics() {
        System.out.println("\n=== Estadísticas de Flyweight ===");
        System.out.println("Tipos de mesa únicos creados: " + tableTypes.size());
        System.out.println("Memoria optimizada mediante compartición de objetos");
    }
}
