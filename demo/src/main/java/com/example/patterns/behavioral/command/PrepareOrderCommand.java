package com.example.patterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PrepareOrderCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(PrepareOrderCommand.class);
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
        logger.info("Deshaciendo preparación de orden (no implementado en cocina real)");
    }

    @Override
    public String getDescription() {
        return "Preparar orden";
    }
}
