package com.example.patterns.structural.flyweight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Fábrica de Flyweight
 */
public class TableTypeFactory {
    private static final Logger logger = LoggerFactory.getLogger(TableTypeFactory.class);
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
        logger.info("\n=== Estadísticas de Flyweight ===");
        logger.info("Tipos de mesa únicos creados: {}", tableTypes.size());
        logger.info("Memoria optimizada mediante compartición de objetos");
    }
}
