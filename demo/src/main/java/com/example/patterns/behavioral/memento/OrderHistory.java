package com.example.patterns.behavioral.memento;

import java.util.Stack;

/**
 * Caretaker - Gestiona los mementos
 */
public class OrderHistory {
    private Stack<OrderMemento> history = new Stack<>();

    public void save(Order order) {
        history.push(order.save());
        System.out.println("Estado guardado. Historial: " + history.size() + " estados");
    }

    public void undo(Order order) {
        if (!history.isEmpty()) {
            OrderMemento memento = history.pop();
            order.restore(memento);
            System.out.println("Estado restaurado");
        } else {
            System.out.println("No hay estados previos");
        }
    }

    public int getHistorySize() {
        return history.size();
    }
}
