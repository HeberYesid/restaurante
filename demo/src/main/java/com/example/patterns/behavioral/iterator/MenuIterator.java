package com.example.patterns.behavioral.iterator;

/**
 * Interfaz Iterator
 */
public interface MenuIterator {
    boolean hasNext();
    DailySpecial next();
    void reset();
}
