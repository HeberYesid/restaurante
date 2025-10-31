package com.example.patterns.behavioral.command;

public class RemoveItemCommand implements Command {
    private KitchenOrder order;
    private String item;

    public RemoveItemCommand(KitchenOrder order, String item) {
        this.order = order;
        this.item = item;
    }

    @Override
    public void execute() {
        order.removeItem(item);
    }

    @Override
    public void undo() {
        order.addItem(item);
    }

    @Override
    public String getDescription() {
        return "Remover " + item;
    }
}
