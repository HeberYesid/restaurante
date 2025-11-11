package com.example.patterns.behavioral.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * Caretaker - Gestiona los mementos
 */
public class OrderHistory {
    private static final Logger logger = LoggerFactory.getLogger(OrderHistory.class);
    private Stack<OrderMemento> history = new Stack<>();

    public void save(Order order) {
        history.push(order.save());
        logger.info("Estado guardado. Historial: {} estados", history.size());
    }

    public void undo(Order order) {
        if (!history.isEmpty()) {
            OrderMemento memento = history.pop();
            order.restore(memento);
            logger.info("Estado restaurado");
        } else {
            logger.info("No hay estados previos");
        }
    }

    public int getHistorySize() {
        return history.size();
    }
}
