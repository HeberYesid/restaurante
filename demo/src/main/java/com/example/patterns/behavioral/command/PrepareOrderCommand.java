package com.example.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class PrepareOrderCommand implements Command {
    private KitchenOrder order;
    private List<String> preparedItems;

    public PrepareOrderCommand(KitchenOrder order) {
        this.order = order;
    }

    @Override
    public void execute() {
        preparedItems = new ArrayList<>(order.getItems());
        order.prepareOrder();
    }

    @Override
    public void undo() {
        System.out.println("Deshaciendo preparación de orden (no implementado en cocina real)");
    }

    @Override
    public String getDescription() {
        return "Preparar orden";
    }
}
