package com.example.patterns.behavioral.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Command Pattern Tests")
class CommandPatternTest {

    private KitchenOrder order;
    private Waiter waiter;

    @BeforeEach
    void setUp() {
        order = new KitchenOrder();
        waiter = new Waiter();
    }

    @Nested
    @DisplayName("AddItemCommand Tests")
    class AddItemCommandTests {

        @Test
        @DisplayName("Should add item when execute is called")
        void shouldAddItemWhenExecuteCalled() {
            Command addCommand = new AddItemCommand(order, "Pizza Margherita");
            
            addCommand.execute();
            
            assertThat(order.getItems()).containsExactly("Pizza Margherita");
        }

        @Test
        @DisplayName("Should remove item when undo is called")
        void shouldRemoveItemWhenUndoCalled() {
            Command addCommand = new AddItemCommand(order, "Pasta Carbonara");
            
            addCommand.execute();
            addCommand.undo();
            
            assertThat(order.getItems()).isEmpty();
        }

        @Test
        @DisplayName("Should return correct description")
        void shouldReturnCorrectDescription() {
            Command addCommand = new AddItemCommand(order, "Lasagna");
            
            assertThat(addCommand.getDescription()).isEqualTo("Agregar Lasagna");
        }

        @Test
        @DisplayName("Should add multiple items")
        void shouldAddMultipleItems() {
            Command add1 = new AddItemCommand(order, "Pizza");
            Command add2 = new AddItemCommand(order, "Pasta");
            Command add3 = new AddItemCommand(order, "Salad");
            
            add1.execute();
            add2.execute();
            add3.execute();
            
            assertThat(order.getItems())
                .hasSize(3)
                .containsExactly("Pizza", "Pasta", "Salad");
        }
    }

    @Nested
    @DisplayName("RemoveItemCommand Tests")
    class RemoveItemCommandTests {

        @Test
        @DisplayName("Should remove item when execute is called")
        void shouldRemoveItemWhenExecuteCalled() {
            order.addItem("Pizza");
            order.addItem("Pasta");
            Command removeCommand = new RemoveItemCommand(order, "Pizza");
            
            removeCommand.execute();
            
            assertThat(order.getItems()).containsExactly("Pasta");
        }

        @Test
        @DisplayName("Should add back item when undo is called")
        void shouldAddBackItemWhenUndoCalled() {
            order.addItem("Pizza");
            order.addItem("Pasta");
            Command removeCommand = new RemoveItemCommand(order, "Pizza");
            
            removeCommand.execute();
            removeCommand.undo();
            
            assertThat(order.getItems()).containsExactlyInAnyOrder("Pizza", "Pasta");
        }

        @Test
        @DisplayName("Should return correct description")
        void shouldReturnCorrectDescription() {
            Command removeCommand = new RemoveItemCommand(order, "Tiramisu");
            
            assertThat(removeCommand.getDescription()).isEqualTo("Remover Tiramisu");
        }

        @Test
        @DisplayName("Should handle removing non-existent item")
        void shouldHandleRemovingNonExistentItem() {
            order.addItem("Pizza");
            Command removeCommand = new RemoveItemCommand(order, "Pasta");
            
            removeCommand.execute();
            
            assertThat(order.getItems()).containsExactly("Pizza");
        }
    }

    @Nested
    @DisplayName("PrepareOrderCommand Tests")
    class PrepareOrderCommandTests {

        @Test
        @DisplayName("Should prepare order when execute is called")
        void shouldPrepareOrderWhenExecuteCalled() {
            order.addItem("Burger");
            order.addItem("Fries");
            Command prepareCommand = new PrepareOrderCommand(order);
            
            prepareCommand.execute();
            
            assertThat(order.getItems()).containsExactly("Burger", "Fries");
        }

        @Test
        @DisplayName("Should return correct description")
        void shouldReturnCorrectDescription() {
            Command prepareCommand = new PrepareOrderCommand(order);
            
            assertThat(prepareCommand.getDescription()).isEqualTo("Preparar orden");
        }

        @Test
        @DisplayName("Should execute undo without errors")
        void shouldExecuteUndoWithoutErrors() {
            order.addItem("Steak");
            Command prepareCommand = new PrepareOrderCommand(order);
            
            prepareCommand.execute();
            prepareCommand.undo();
            
            assertThat(order.getItems()).containsExactly("Steak");
        }

        @Test
        @DisplayName("Should prepare empty order")
        void shouldPrepareEmptyOrder() {
            Command prepareCommand = new PrepareOrderCommand(order);
            
            prepareCommand.execute();
            
            assertThat(order.getItems()).isEmpty();
        }
    }

    @Nested
    @DisplayName("KitchenOrder Tests")
    class KitchenOrderTests {

        @Test
        @DisplayName("Should add items to order")
        void shouldAddItemsToOrder() {
            order.addItem("Caesar Salad");
            order.addItem("Greek Salad");
            
            assertThat(order.getItems())
                .hasSize(2)
                .containsExactly("Caesar Salad", "Greek Salad");
        }

        @Test
        @DisplayName("Should remove items from order")
        void shouldRemoveItemsFromOrder() {
            order.addItem("Item1");
            order.addItem("Item2");
            order.addItem("Item3");
            
            order.removeItem("Item2");
            
            assertThat(order.getItems()).containsExactly("Item1", "Item3");
        }

        @Test
        @DisplayName("Should prepare order with items")
        void shouldPrepareOrderWithItems() {
            order.addItem("Soup");
            order.addItem("Sandwich");
            
            order.prepareOrder();
            
            assertThat(order.getItems()).containsExactly("Soup", "Sandwich");
        }

        @Test
        @DisplayName("Should cancel order and clear all items")
        void shouldCancelOrderAndClearAllItems() {
            order.addItem("Pizza");
            order.addItem("Pasta");
            order.addItem("Dessert");
            
            order.cancelOrder();
            
            assertThat(order.getItems()).isEmpty();
        }

        @Test
        @DisplayName("Should return independent copy of items")
        void shouldReturnIndependentCopyOfItems() {
            order.addItem("Item1");
            var items1 = order.getItems();
            var items2 = order.getItems();
            
            items1.add("Item2");
            
            assertThat(order.getItems()).hasSize(1);
            assertThat(items2).hasSize(1);
        }
    }

    @Nested
    @DisplayName("Waiter Tests")
    class WaiterTests {

        @Test
        @DisplayName("Should execute command and add to history")
        void shouldExecuteCommandAndAddToHistory() {
            Command addCommand = new AddItemCommand(order, "Espresso");
            
            waiter.takeOrder(addCommand);
            
            assertThat(order.getItems()).containsExactly("Espresso");
        }

        @Test
        @DisplayName("Should undo last command")
        void shouldUndoLastCommand() {
            Command addCommand = new AddItemCommand(order, "Cappuccino");
            
            waiter.takeOrder(addCommand);
            waiter.undoLastOrder();
            
            assertThat(order.getItems()).isEmpty();
        }

        @Test
        @DisplayName("Should handle undo with empty history")
        void shouldHandleUndoWithEmptyHistory() {
            waiter.undoLastOrder();
            
            assertThat(order.getItems()).isEmpty();
        }

        @Test
        @DisplayName("Should handle multiple commands and undos")
        void shouldHandleMultipleCommandsAndUndos() {
            Command add1 = new AddItemCommand(order, "Coffee");
            Command add2 = new AddItemCommand(order, "Tea");
            Command add3 = new AddItemCommand(order, "Juice");
            
            waiter.takeOrder(add1);
            waiter.takeOrder(add2);
            waiter.takeOrder(add3);
            
            assertThat(order.getItems()).hasSize(3);
            
            waiter.undoLastOrder();
            assertThat(order.getItems()).containsExactly("Coffee", "Tea");
            
            waiter.undoLastOrder();
            assertThat(order.getItems()).containsExactly("Coffee");
        }

        @Test
        @DisplayName("Should show history correctly")
        void shouldShowHistoryCorrectly() {
            Command add1 = new AddItemCommand(order, "Item1");
            Command add2 = new AddItemCommand(order, "Item2");
            
            waiter.takeOrder(add1);
            waiter.takeOrder(add2);
            
            waiter.showHistory();
            
            assertThat(order.getItems()).hasSize(2);
        }

        @Test
        @DisplayName("Should show empty history")
        void shouldShowEmptyHistory() {
            waiter.showHistory();
            
            assertThat(order.getItems()).isEmpty();
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should handle complete order workflow")
        void shouldHandleCompleteOrderWorkflow() {
            waiter.takeOrder(new AddItemCommand(order, "Appetizer"));
            waiter.takeOrder(new AddItemCommand(order, "Main Course"));
            waiter.takeOrder(new AddItemCommand(order, "Dessert"));
            waiter.takeOrder(new AddItemCommand(order, "Drink"));
            
            assertThat(order.getItems()).hasSize(4);
            
            waiter.takeOrder(new RemoveItemCommand(order, "Dessert"));
            assertThat(order.getItems()).hasSize(3);
            
            waiter.takeOrder(new PrepareOrderCommand(order));
            assertThat(order.getItems()).hasSize(3);
        }

        @Test
        @DisplayName("Should handle complex undo scenario")
        void shouldHandleComplexUndoScenario() {
            waiter.takeOrder(new AddItemCommand(order, "Pizza"));
            waiter.takeOrder(new AddItemCommand(order, "Pasta"));
            waiter.takeOrder(new RemoveItemCommand(order, "Pizza"));
            
            assertThat(order.getItems()).containsExactly("Pasta");
            
            waiter.undoLastOrder(); // Undo remove
            assertThat(order.getItems()).containsExactlyInAnyOrder("Pizza", "Pasta");
            
            waiter.undoLastOrder(); // Undo add Pasta
            assertThat(order.getItems()).containsExactly("Pizza");
            
            waiter.undoLastOrder(); // Undo add Pizza
            assertThat(order.getItems()).isEmpty();
        }

        @Test
        @DisplayName("Should handle order modification and preparation")
        void shouldHandleOrderModificationAndPreparation() {
            waiter.takeOrder(new AddItemCommand(order, "Steak"));
            waiter.takeOrder(new AddItemCommand(order, "Salad"));
            waiter.takeOrder(new AddItemCommand(order, "Wine"));
            
            waiter.undoLastOrder(); // Remove Wine
            
            waiter.takeOrder(new AddItemCommand(order, "Water"));
            waiter.takeOrder(new PrepareOrderCommand(order));
            
            assertThat(order.getItems()).containsExactly("Steak", "Salad", "Water");
        }
    }
}
