package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * Invoker - Ejecutor de comandos con soporte de undo
 */
public class Waiter {
    private static final Logger logger = LoggerFactory.getLogger(Waiter.class);
    private Stack<Command> commandHistory = new Stack<>();

    public void takeOrder(Command command) {
        command.execute();
        commandHistory.push(command);
        logger.info("Comando ejecutado: {}", command.getDescription());
    }

    public void undoLastOrder() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            logger.info("Comando deshecho: {}", command.getDescription());
        } else {
            logger.info("No hay comandos para deshacer");
        }
    }

    public void showHistory() {
        logger.info("\n=== Historial de comandos ===");
        if (commandHistory.isEmpty()) {
            logger.info("No hay comandos en el historial");
        } else {
            commandHistory.forEach(cmd -> logger.info("  - {}", cmd.getDescription()));
        }
    }
}
