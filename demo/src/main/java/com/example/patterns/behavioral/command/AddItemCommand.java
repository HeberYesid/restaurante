package com.example.patterns.behavioral.command;

public class AddItemCommand implements Command {
    private KitchenOrder order;
    private String item;

    public AddItemCommand(KitchenOrder order, String item) {
        this.order = order;
        this.item = item;
    }

    @Override
    public void execute() {
        order.addItem(item);
    }

    @Override
    public void undo() {
        order.removeItem(item);
    }

    @Override
    public String getDescription() {
        return "Agregar " + item;
    }
}
