package com.example.patterns.behavioral.command;

import java.util.Stack;

/**
 * Invoker - Ejecutor de comandos con soporte de undo
 */
public class Waiter {
    private Stack<Command> commandHistory = new Stack<>();

    public void takeOrder(Command command) {
        command.execute();
        commandHistory.push(command);
        System.out.println("Comando ejecutado: " + command.getDescription());
    }

    public void undoLastOrder() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            System.out.println("Comando deshecho: " + command.getDescription());
        } else {
            System.out.println("No hay comandos para deshacer");
        }
    }

    public void showHistory() {
        System.out.println("\n=== Historial de comandos ===");
        if (commandHistory.isEmpty()) {
            System.out.println("No hay comandos en el historial");
        } else {
            commandHistory.forEach(cmd -> System.out.println("  - " + cmd.getDescription()));
        }
    }
}
