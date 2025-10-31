package com.example.patterns.behavioral.command;

/**
 * Patrón Command - Interfaz de comando
 */
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
