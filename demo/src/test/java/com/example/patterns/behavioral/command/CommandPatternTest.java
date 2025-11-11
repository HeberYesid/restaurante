package com.example.patterns.behavioral.command;package com.example.patterns.behavioral.command;



import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Nested;import org.junit.jupiter.api.Nested;

import org.junit.jupiter.api.Test;import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.BeforeEach;



import static org.assertj.core.api.Assertions.*;import static org.assertj.core.api.Assertions.*;



@DisplayName("Patrón Command - Tests de Cobertura Completa")@DisplayName("Patrón Command - Tests de Cobertura Completa")

class CommandPatternTest {class CommandPatternTest {



    @Nested    @Nested

    @DisplayName("Waiter - Invoker (Ejecutor de Comandos)")    @DisplayName("KitchenInvoker - Invocador de Comandos de Cocina")

    class WaiterTests {    class KitchenInvokerTests {



        private Waiter waiter;        private KitchenInvoker invoker;

        private KitchenOrder order;        private Kitchen kitchen;



        @BeforeEach        @BeforeEach

        void setUp() {        void setUp() {

            waiter = new Waiter();            invoker = new KitchenInvoker();

            order = new KitchenOrder();            kitchen = new Kitchen();

        }        }



        @Test        @Test

        @DisplayName("Debe ejecutar comando de agregar item")        @DisplayName("Debe ejecutar comando de preparar plato")

        void shouldExecuteAddItemCommand() {        void shouldExecutePrepareCommand() {

            // Given            // Given

            Command addPizza = new AddItemCommand(order, "Pizza");            Command preparePizza = new PrepareDishCommand(kitchen, "Pizza Margherita");



            // When            // When

            waiter.takeOrder(addPizza);            invoker.setCommand(preparePizza);

            invoker.executeCommand();

            // Then

            assertThat(order.getItems()).containsExactly("Pizza");            // Then

        }            assertThat(kitchen.getCurrentDish()).isEqualTo("Pizza Margherita");

        }

        @Test

        @DisplayName("Debe deshacer el último comando")        @Test

        void shouldUndoLastCommand() {        @DisplayName("Debe ejecutar comando de servir plato")

            // Given        void shouldExecuteServeCommand() {

            Command addPasta = new AddItemCommand(order, "Pasta");            // Given

            waiter.takeOrder(addPasta);            kitchen.prepareDish("Pasta Carbonara");

            assertThat(order.getItems()).containsExactly("Pasta");            Command serveCommand = new ServeDishCommand(kitchen);



            // When            // When

            waiter.undoLastOrder();            invoker.setCommand(serveCommand);

            invoker.executeCommand();

            // Then

            assertThat(order.getItems()).isEmpty();            // Then

        }            assertThat(kitchen.getCurrentDish()).isNull();

        }

        @Test

        @DisplayName("Debe ejecutar múltiples comandos secuencialmente")        @Test

        void shouldExecuteMultipleCommands() {        @DisplayName("Debe ejecutar múltiples comandos secuencialmente")

            // Given        void shouldExecuteMultipleCommandsSequentially() {

            Command cmd1 = new AddItemCommand(order, "Ensalada");            // Given

            Command cmd2 = new AddItemCommand(order, "Sopa");            Command prepare1 = new PrepareDishCommand(kitchen, "Ensalada César");

            Command cmd3 = new AddItemCommand(order, "Postre");            Command serve1 = new ServeDishCommand(kitchen);

            Command prepare2 = new PrepareDishCommand(kitchen, "Salmón a la parrilla");

            // When

            waiter.takeOrder(cmd1);            // When

            waiter.takeOrder(cmd2);            invoker.setCommand(prepare1);

            waiter.takeOrder(cmd3);            invoker.executeCommand();

            assertThat(kitchen.getCurrentDish()).isEqualTo("Ensalada César");

            // Then

            assertThat(order.getItems()).containsExactly("Ensalada", "Sopa", "Postre");            invoker.setCommand(serve1);

        }            invoker.executeCommand();

            assertThat(kitchen.getCurrentDish()).isNull();

        @Test

        @DisplayName("Debe deshacer múltiples comandos en orden inverso")            invoker.setCommand(prepare2);

        void shouldUndoMultipleCommandsInReverseOrder() {            invoker.executeCommand();

            // Given

            Command cmd1 = new AddItemCommand(order, "Item 1");            // Then

            Command cmd2 = new AddItemCommand(order, "Item 2");            assertThat(kitchen.getCurrentDish()).isEqualTo("Salmón a la parrilla");

            Command cmd3 = new AddItemCommand(order, "Item 3");        }

            

            waiter.takeOrder(cmd1);        @Test

            waiter.takeOrder(cmd2);        @DisplayName("Debe permitir cambiar comando antes de ejecutar")

            waiter.takeOrder(cmd3);        void shouldAllowChangingCommandBeforeExecution() {

            // Given

            // When: Deshacer último comando            Command command1 = new PrepareDishCommand(kitchen, "Plato A");

            waiter.undoLastOrder();            Command command2 = new PrepareDishCommand(kitchen, "Plato B");

            // Then

            assertThat(order.getItems()).containsExactly("Item 1", "Item 2");            // When

            invoker.setCommand(command1);

            // When: Deshacer penúltimo            invoker.setCommand(command2); // Sobrescribe

            waiter.undoLastOrder();            invoker.executeCommand();

            // Then

            assertThat(order.getItems()).containsExactly("Item 1");            // Then

            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato B");

            // When: Deshacer primero        }

            waiter.undoLastOrder();    }

            // Then

            assertThat(order.getItems()).isEmpty();    @Nested

        }    @DisplayName("PrepareDishCommand - Comando de Preparación")

    class PrepareDishCommandTests {

        @Test

        @DisplayName("Debe manejar undo sin comandos previos")        private Kitchen kitchen;

        void shouldHandleUndoWithoutCommands() {

            // When: Intentar deshacer sin comandos        @BeforeEach

            waiter.undoLastOrder();        void setUp() {

            kitchen = new Kitchen();

            // Then: No debe lanzar excepción        }

            assertThat(order.getItems()).isEmpty();

        }        @Test

        @DisplayName("Debe preparar plato específico")

        @Test        void shouldPrepareDish() {

        @DisplayName("Debe mostrar historial de comandos")            // Given

        void shouldShowCommandHistory() {            Command command = new PrepareDishCommand(kitchen, "Hamburguesa");

            // Given

            Command cmd1 = new AddItemCommand(order, "Hamburguesa");            // When

            Command cmd2 = new PrepareOrderCommand(order);            command.execute();



            // When            // Then

            waiter.takeOrder(cmd1);            assertThat(kitchen.getCurrentDish()).isEqualTo("Hamburguesa");

            waiter.takeOrder(cmd2);        }

            waiter.showHistory();

        @Test

            // Then: El método no lanza excepciones (verifica logs)        @DisplayName("Debe permitir preparar diferentes platos")

            assertThat(order.getItems()).isNotEmpty();        void shouldPrepareDifferentDishes() {

        }            // Given

    }            Command pizza = new PrepareDishCommand(kitchen, "Pizza");

            Command pasta = new PrepareDishCommand(kitchen, "Pasta");

    @Nested

    @DisplayName("AddItemCommand - Comando de Agregar Item")            // When

    class AddItemCommandTests {            pizza.execute();

            assertThat(kitchen.getCurrentDish()).isEqualTo("Pizza");

        private KitchenOrder order;

            pasta.execute();

        @BeforeEach

        void setUp() {            // Then

            order = new KitchenOrder();            assertThat(kitchen.getCurrentDish()).isEqualTo("Pasta");

        }        }



        @Test        @Test

        @DisplayName("Debe agregar item a la orden")        @DisplayName("Debe manejar platos con nombres largos")

        void shouldAddItemToOrder() {        void shouldHandleLongDishNames() {

            // Given            // Given

            Command command = new AddItemCommand(order, "Tacos");            String longName = "Salmón glaseado con salsa de maracuyá y vegetales asados";

            Command command = new PrepareDishCommand(kitchen, longName);

            // When

            command.execute();            // When

            command.execute();

            // Then

            assertThat(order.getItems()).containsExactly("Tacos");            // Then

        }            assertThat(kitchen.getCurrentDish()).isEqualTo(longName);

        }

        @Test

        @DisplayName("Debe deshacer agregado de item")        @Test

        void shouldUndoAddItem() {        @DisplayName("Debe manejar platos con nombres vacíos")

            // Given        void shouldHandleEmptyDishName() {

            Command command = new AddItemCommand(order, "Burrito");            // Given

            command.execute();            Command command = new PrepareDishCommand(kitchen, "");

            assertThat(order.getItems()).containsExactly("Burrito");

            // When

            // When            command.execute();

            command.undo();

            // Then

            // Then            assertThat(kitchen.getCurrentDish()).isEqualTo("");

            assertThat(order.getItems()).isEmpty();        }

        }    }



        @Test    @Nested

        @DisplayName("Debe proporcionar descripción del comando")    @DisplayName("ServeDishCommand - Comando de Servir")

        void shouldProvideCommandDescription() {    class ServeDishCommandTests {

            // Given

            Command command = new AddItemCommand(order, "Quesadilla");        private Kitchen kitchen;



            // When        @BeforeEach

            String description = command.getDescription();        void setUp() {

            kitchen = new Kitchen();

            // Then        }

            assertThat(description).isEqualTo("Agregar Quesadilla");

        }        @Test

        @DisplayName("Debe servir plato preparado")

        @Test        void shouldServePreparedDish() {

        @DisplayName("Debe permitir agregar múltiples items diferentes")            // Given

        void shouldAllowAddingMultipleDifferentItems() {            kitchen.prepareDish("Paella");

            // Given            Command command = new ServeDishCommand(kitchen);

            Command cmd1 = new AddItemCommand(order, "Plato A");

            Command cmd2 = new AddItemCommand(order, "Plato B");            // When

            Command cmd3 = new AddItemCommand(order, "Plato C");            command.execute();



            // When            // Then

            cmd1.execute();            assertThat(kitchen.getCurrentDish()).isNull();

            cmd2.execute();        }

            cmd3.execute();

        @Test

            // Then        @DisplayName("Debe manejar servir cuando no hay plato preparado")

            assertThat(order.getItems()).containsExactly("Plato A", "Plato B", "Plato C");        void shouldHandleServingWhenNoDishPrepared() {

        }            // Given

            Command command = new ServeDishCommand(kitchen);

        @Test            assertThat(kitchen.getCurrentDish()).isNull();

        @DisplayName("Debe permitir agregar item duplicado")

        void shouldAllowAddingDuplicateItem() {            // When

            // Given            command.execute();

            Command cmd1 = new AddItemCommand(order, "Cerveza");

            Command cmd2 = new AddItemCommand(order, "Cerveza");            // Then

            assertThat(kitchen.getCurrentDish()).isNull();

            // When        }

            cmd1.execute();

            cmd2.execute();        @Test

        @DisplayName("Servir múltiples veces debe mantener cocina vacía")

            // Then        void shouldKeepKitchenEmptyAfterMultipleServes() {

            assertThat(order.getItems()).containsExactly("Cerveza", "Cerveza");            // Given

        }            Command command = new ServeDishCommand(kitchen);

    }

            // When

    @Nested            command.execute();

    @DisplayName("RemoveItemCommand - Comando de Remover Item")            command.execute();

    class RemoveItemCommandTests {            command.execute();



        private KitchenOrder order;            // Then

            assertThat(kitchen.getCurrentDish()).isNull();

        @BeforeEach        }

        void setUp() {    }

            order = new KitchenOrder();

            order.addItem("Pizza");    @Nested

            order.addItem("Pasta");    @DisplayName("Kitchen - Receptor de Comandos")

            order.addItem("Ensalada");    class KitchenTests {

        }

        private Kitchen kitchen;

        @Test

        @DisplayName("Debe remover item de la orden")        @BeforeEach

        void shouldRemoveItemFromOrder() {        void setUp() {

            // Given            kitchen = new Kitchen();

            Command command = new RemoveItemCommand(order, "Pasta");        }



            // When        @Test

            command.execute();        @DisplayName("Debe iniciar sin plato preparado")

        void shouldStartWithoutDish() {

            // Then            // Then

            assertThat(order.getItems()).containsExactly("Pizza", "Ensalada");            assertThat(kitchen.getCurrentDish()).isNull();

        }        }



        @Test        @Test

        @DisplayName("Debe deshacer remoción de item")        @DisplayName("Debe preparar plato correctamente")

        void shouldUndoRemoveItem() {        void shouldPrepareDishCorrectly() {

            // Given            // When

            Command command = new RemoveItemCommand(order, "Pizza");            kitchen.prepareDish("Risotto");

            command.execute();

            assertThat(order.getItems()).containsExactly("Pasta", "Ensalada");            // Then

            assertThat(kitchen.getCurrentDish()).isEqualTo("Risotto");

            // When        }

            command.undo();

        @Test

            // Then        @DisplayName("Debe servir plato y limpiar estado")

            assertThat(order.getItems()).contains("Pizza");        void shouldServeDishAndClearState() {

        }            // Given

            kitchen.prepareDish("Lasaña");

        @Test            assertThat(kitchen.getCurrentDish()).isEqualTo("Lasaña");

        @DisplayName("Debe proporcionar descripción del comando")

        void shouldProvideCommandDescription() {            // When

            // Given            kitchen.serveDish();

            Command command = new RemoveItemCommand(order, "Ensalada");

            // Then

            // When            assertThat(kitchen.getCurrentDish()).isNull();

            String description = command.getDescription();        }



            // Then        @Test

            assertThat(description).isEqualTo("Remover Ensalada");        @DisplayName("Debe permitir preparar nuevo plato después de servir")

        }        void shouldAllowPreparingNewDishAfterServing() {

            // Given

        @Test            kitchen.prepareDish("Plato 1");

        @DisplayName("Debe manejar remoción de item inexistente")            kitchen.serveDish();

        void shouldHandleRemovingNonExistentItem() {

            // Given            // When

            Command command = new RemoveItemCommand(order, "Item Inexistente");            kitchen.prepareDish("Plato 2");



            // When            // Then

            command.execute();            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato 2");

        }

            // Then: Orden no cambia

            assertThat(order.getItems()).containsExactly("Pizza", "Pasta", "Ensalada");        @Test

        }        @DisplayName("Preparar nuevo plato debe reemplazar plato actual")

    }        void shouldReplaceCurrentDishWhenPreparingNew() {

            // Given

    @Nested            kitchen.prepareDish("Plato Original");

    @DisplayName("PrepareOrderCommand - Comando de Preparar Orden")

    class PrepareOrderCommandTests {            // When

            kitchen.prepareDish("Plato Nuevo");

        private KitchenOrder order;

            // Then

        @BeforeEach            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato Nuevo");

        void setUp() {        }

            order = new KitchenOrder();    }

            order.addItem("Hamburguesa");

            order.addItem("Papas fritas");    @Nested

        }    @DisplayName("Escenarios de Negocio Realistas")

    class BusinessScenariosTests {

        @Test

        @DisplayName("Debe ejecutar preparación de orden")        @Test

        void shouldExecutePrepareOrder() {        @DisplayName("Escenario 1: Flujo completo de orden de restaurante")

            // Given        void scenario1_CompleteRestaurantOrderFlow() {

            Command command = new PrepareOrderCommand(order);            // Given: Setup cocina y sistema de comandos

            Kitchen kitchen = new Kitchen();

            // When            KitchenInvoker invoker = new KitchenInvoker();

            command.execute();

            // When: Cliente ordena entrada

            // Then: No lanza excepción (verifica logs)            Command prepareAppetizer = new PrepareDishCommand(kitchen, "Bruschetta");

            assertThat(order.getItems()).isNotEmpty();            invoker.setCommand(prepareAppetizer);

        }            invoker.executeCommand();

            assertThat(kitchen.getCurrentDish()).isEqualTo("Bruschetta");

        @Test

        @DisplayName("Debe deshacer preparación (cancelar orden)")            // Then: Mesero sirve entrada

        void shouldUndoPrepareOrder() {            Command serveAppetizer = new ServeDishCommand(kitchen);

            // Given            invoker.setCommand(serveAppetizer);

            Command command = new PrepareOrderCommand(order);            invoker.executeCommand();

            command.execute();            assertThat(kitchen.getCurrentDish()).isNull();



            // When            // When: Cliente ordena plato principal

            command.undo();            Command prepareMain = new PrepareDishCommand(kitchen, "Filete Mignon");

            invoker.setCommand(prepareMain);

            // Then            invoker.executeCommand();

            assertThat(order.getItems()).isEmpty();            assertThat(kitchen.getCurrentDish()).isEqualTo("Filete Mignon");

        }

            // Then: Mesero sirve plato principal

        @Test            Command serveMain = new ServeDishCommand(kitchen);

        @DisplayName("Debe proporcionar descripción del comando")            invoker.setCommand(serveMain);

        void shouldProvideCommandDescription() {            invoker.executeCommand();

            // Given            assertThat(kitchen.getCurrentDish()).isNull();

            Command command = new PrepareOrderCommand(order);

            // When: Cliente ordena postre

            // When            Command prepareDessert = new PrepareDishCommand(kitchen, "Tiramisú");

            String description = command.getDescription();            invoker.setCommand(prepareDessert);

            invoker.executeCommand();

            // Then            assertThat(kitchen.getCurrentDish()).isEqualTo("Tiramisú");

            assertThat(description).isEqualTo("Preparar orden");

        }            // Then: Mesero sirve postre

            Command serveDessert = new ServeDishCommand(kitchen);

        @Test            invoker.setCommand(serveDessert);

        @DisplayName("Debe preparar orden vacía sin errores")            invoker.executeCommand();

        void shouldPrepareEmptyOrderWithoutError() {

            // Given            // Finally: Cocina vacía, orden completa

            KitchenOrder emptyOrder = new KitchenOrder();            assertThat(kitchen.getCurrentDish()).isNull();

            Command command = new PrepareOrderCommand(emptyOrder);        }



            // When        @Test

            command.execute();        @DisplayName("Escenario 2: Múltiples órdenes paralelas (diferentes cocinas)")

        void scenario2_MultipleParallelOrders() {

            // Then: No lanza excepción            // Given: Dos estaciones de cocina

            assertThat(emptyOrder.getItems()).isEmpty();            Kitchen grill = new Kitchen();

        }            Kitchen pastry = new Kitchen();

    }            KitchenInvoker grillInvoker = new KitchenInvoker();

            KitchenInvoker pastryInvoker = new KitchenInvoker();

    @Nested

    @DisplayName("KitchenOrder - Receptor de Comandos")            // When: Preparar en grill

    class KitchenOrderTests {            Command grillCommand = new PrepareDishCommand(grill, "Steak");

            grillInvoker.setCommand(grillCommand);

        private KitchenOrder order;            grillInvoker.executeCommand();



        @BeforeEach            // And: Preparar en pastelería

        void setUp() {            Command pastryCommand = new PrepareDishCommand(pastry, "Tarta de manzana");

            order = new KitchenOrder();            pastryInvoker.setCommand(pastryCommand);

        }            pastryInvoker.executeCommand();



        @Test            // Then: Ambas cocinas trabajando simultáneamente

        @DisplayName("Debe iniciar con orden vacía")            assertThat(grill.getCurrentDish()).isEqualTo("Steak");

        void shouldStartWithEmptyOrder() {            assertThat(pastry.getCurrentDish()).isEqualTo("Tarta de manzana");

            // Then

            assertThat(order.getItems()).isEmpty();            // When: Servir ambos platos

        }            grillInvoker.setCommand(new ServeDishCommand(grill));

            grillInvoker.executeCommand();

        @Test            pastryInvoker.setCommand(new ServeDishCommand(pastry));

        @DisplayName("Debe agregar item correctamente")            pastryInvoker.executeCommand();

        void shouldAddItemCorrectly() {

            // When            // Then: Ambas cocinas vacías

            order.addItem("Sushi");            assertThat(grill.getCurrentDish()).isNull();

            assertThat(pastry.getCurrentDish()).isNull();

            // Then        }

            assertThat(order.getItems()).containsExactly("Sushi");

        }        @Test

        @DisplayName("Escenario 3: Cambio de orden antes de preparar")

        @Test        void scenario3_ChangeOrderBeforePreparing() {

        @DisplayName("Debe remover item correctamente")            // Given

        void shouldRemoveItemCorrectly() {            Kitchen kitchen = new Kitchen();

            // Given            KitchenInvoker invoker = new KitchenInvoker();

            order.addItem("Item 1");

            order.addItem("Item 2");            // When: Cliente cambia de opinión

            Command originalOrder = new PrepareDishCommand(kitchen, "Pizza Hawaiana");

            // When            invoker.setCommand(originalOrder);

            order.removeItem("Item 1");

            // Cliente cambia de opinión ANTES de enviar orden

            // Then            Command newOrder = new PrepareDishCommand(kitchen, "Pizza Pepperoni");

            assertThat(order.getItems()).containsExactly("Item 2");            invoker.setCommand(newOrder);

        }            invoker.executeCommand();



        @Test            // Then: Se prepara la orden final

        @DisplayName("Debe preparar orden sin errores")            assertThat(kitchen.getCurrentDish()).isEqualTo("Pizza Pepperoni");

        void shouldPrepareOrderWithoutError() {        }

            // Given

            order.addItem("Plato 1");        @Test

            order.addItem("Plato 2");        @DisplayName("Escenario 4: Cocina ocupada reemplaza plato anterior")

        void scenario4_BusyKitchenReplacesOldDish() {

            // When            // Given: Cocina con plato en preparación

            order.prepareOrder();            Kitchen kitchen = new Kitchen();

            KitchenInvoker invoker = new KitchenInvoker();

            // Then: No lanza excepción

            assertThat(order.getItems()).hasSize(2);            // When: Primera orden

        }            invoker.setCommand(new PrepareDishCommand(kitchen, "Plato A"));

            invoker.executeCommand();

        @Test            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato A");

        @DisplayName("Debe cancelar orden y limpiar items")

        void shouldCancelOrderAndClearItems() {            // When: Nueva orden urgente sin servir la anterior (error del mesero)

            // Given            invoker.setCommand(new PrepareDishCommand(kitchen, "Plato B"));

            order.addItem("Item A");            invoker.executeCommand();

            order.addItem("Item B");

            order.addItem("Item C");            // Then: Plato B reemplaza a Plato A

            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato B");

            // When        }

            order.cancelOrder();

        @Test

            // Then        @DisplayName("Escenario 5: Turno de restaurante con múltiples platos")

            assertThat(order.getItems()).isEmpty();        void scenario5_RestaurantShiftWithMultipleDishes() {

        }            // Given

            Kitchen kitchen = new Kitchen();

        @Test            KitchenInvoker invoker = new KitchenInvoker();

        @DisplayName("Debe retornar copia de items (inmutabilidad)")            int dishesServed = 0;

        void shouldReturnCopyOfItems() {

            // Given            // Simular 10 órdenes durante el turno

            order.addItem("Original");            String[] dishes = {

                            "Ensalada César", "Sopa de cebolla", "Camarones al ajillo",

            // When                "Pasta Alfredo", "Pollo asado", "Salmón teriyaki",

            var items = order.getItems();                "Pizza Cuatro Quesos", "Hamburguesa BBQ", "Tacos al pastor",

            items.add("Modificación externa");                "Helado de vainilla"

            };

            // Then: La orden original no debe cambiar

            assertThat(order.getItems()).containsExactly("Original");            // When: Procesar todas las órdenes

        }            for (String dish : dishes) {

    }                invoker.setCommand(new PrepareDishCommand(kitchen, dish));

                invoker.executeCommand();

    @Nested                assertThat(kitchen.getCurrentDish()).isEqualTo(dish);

    @DisplayName("Escenarios de Negocio Realistas")

    class BusinessScenariosTests {                invoker.setCommand(new ServeDishCommand(kitchen));

                invoker.executeCommand();

        @Test                assertThat(kitchen.getCurrentDish()).isNull();

        @DisplayName("Escenario 1: Cliente ordena comida completa")

        void scenario1_CustomerOrdersCompleteM eal() {                dishesServed++;

            // Given            }

            Waiter waiter = new Waiter();

            KitchenOrder order = new KitchenOrder();            // Then: Turno exitoso

            assertThat(dishesServed).isEqualTo(10);

            // When: Cliente ordena entrada, plato principal y postre            assertThat(kitchen.getCurrentDish()).isNull();

            waiter.takeOrder(new AddItemCommand(order, "Ensalada César"));        }

            waiter.takeOrder(new AddItemCommand(order, "Filete de res"));    }

            waiter.takeOrder(new AddItemCommand(order, "Tiramisú"));

    @Nested

            // Then    @DisplayName("Tests de Encapsulación de Comandos")

            assertThat(order.getItems()).containsExactly(    class CommandEncapsulationTests {

                "Ensalada César", "Filete de res", "Tiramisú"

            );        @Test

        }        @DisplayName("Comandos deben ser objetos independientes")

        void commandsShouldBeIndependentObjects() {

        @Test            // Given

        @DisplayName("Escenario 2: Cliente cambia de opinión (undo)")            Kitchen kitchen1 = new Kitchen();

        void scenario2_CustomerChangesM ind() {            Kitchen kitchen2 = new Kitchen();

            // Given

            Waiter waiter = new Waiter();            Command cmd1 = new PrepareDishCommand(kitchen1, "Plato 1");

            KitchenOrder order = new KitchenOrder();            Command cmd2 = new PrepareDishCommand(kitchen2, "Plato 2");



            // When: Cliente ordena pero cambia de opinión            // When

            waiter.takeOrder(new AddItemCommand(order, "Pizza Hawaiana"));            cmd1.execute();

            assertThat(order.getItems()).containsExactly("Pizza Hawaiana");            cmd2.execute();



            // Cliente se arrepiente            // Then: No hay interferencia entre comandos

            waiter.undoLastOrder();            assertThat(kitchen1.getCurrentDish()).isEqualTo("Plato 1");

            assertThat(kitchen2.getCurrentDish()).isEqualTo("Plato 2");

            // Then: Orden vacía        }

            assertThat(order.getItems()).isEmpty();

        @Test

            // When: Cliente ordena otra cosa        @DisplayName("Mismo comando puede ejecutarse múltiples veces")

            waiter.takeOrder(new AddItemCommand(order, "Pizza Pepperoni"));        void sameCommandCanBeExecutedMultipleTimes() {

            // Given

            // Then            Kitchen kitchen = new Kitchen();

            assertThat(order.getItems()).containsExactly("Pizza Pepperoni");            Command command = new PrepareDishCommand(kitchen, "Pizza");

        }

            // When

        @Test            command.execute();

        @DisplayName("Escenario 3: Cliente agrega y remueve items")            assertThat(kitchen.getCurrentDish()).isEqualTo("Pizza");

        void scenario3_CustomerAddsAndRemovesItems() {

            // Given            kitchen.serveDish(); // Limpiar

            Waiter waiter = new Waiter();

            KitchenOrder order = new KitchenOrder();            command.execute(); // Ejecutar de nuevo



            // When: Cliente ordena varios items            // Then

            waiter.takeOrder(new AddItemCommand(order, "Hamburguesa"));            assertThat(kitchen.getCurrentDish()).isEqualTo("Pizza");

            waiter.takeOrder(new AddItemCommand(order, "Papas fritas"));        }

            waiter.takeOrder(new AddItemCommand(order, "Refresco"));

            waiter.takeOrder(new AddItemCommand(order, "Helado"));        @Test

        @DisplayName("Invoker debe desacoplar cliente de receptor")

            // Cliente decide no querer el helado        void invokerShouldDecoupleClientFromReceiver() {

            waiter.takeOrder(new RemoveItemCommand(order, "Helado"));            // Given: Cliente (test) no conoce Kitchen directamente

            KitchenInvoker invoker = new KitchenInvoker();

            // Then            Kitchen kitchen = new Kitchen();

            assertThat(order.getItems()).containsExactly(

                "Hamburguesa", "Papas fritas", "Refresco"            // When: Cliente usa solo el invoker

            );            Command command = new PrepareDishCommand(kitchen, "Plato X");

        }            invoker.setCommand(command);

            invoker.executeCommand();

        @Test

        @DisplayName("Escenario 4: Flujo completo con preparación")            // Then: Comando ejecutado sin acoplamiento directo

        void scenario4_CompleteFlowWithPreparation() {            assertThat(kitchen.getCurrentDish()).isEqualTo("Plato X");

            // Given        }

            Waiter waiter = new Waiter();    }

            KitchenOrder order = new KitchenOrder();

    @Nested

            // When: Cliente construye su orden    @DisplayName("Tests de Edge Cases")

            waiter.takeOrder(new AddItemCommand(order, "Sopa del día"));    class EdgeCaseTests {

            waiter.takeOrder(new AddItemCommand(order, "Pollo asado"));

            waiter.takeOrder(new AddItemCommand(order, "Flan"));        @Test

        @DisplayName("Debe manejar null en nombre de plato")

            // Mesero envía orden a cocina        void shouldHandleNullDishName() {

            waiter.takeOrder(new PrepareOrderCommand(order));            // Given

            Kitchen kitchen = new Kitchen();

            // Then: Orden contiene todos los items            Command command = new PrepareDishCommand(kitchen, null);

            assertThat(order.getItems()).contains("Sopa del día", "Pollo asado", "Flan");

        }            // When

            command.execute();

        @Test

        @DisplayName("Escenario 5: Cancelación de orden completa")            // Then

        void scenario5_CompleteOrderCancellation() {            assertThat(kitchen.getCurrentDish()).isNull();

            // Given        }

            Waiter waiter = new Waiter();

            KitchenOrder order = new KitchenOrder();        @Test

        @DisplayName("Debe manejar cadenas vacías")

            // When: Cliente ordena        void shouldHandleEmptyStrings() {

            waiter.takeOrder(new AddItemCommand(order, "Plato 1"));            // Given

            waiter.takeOrder(new AddItemCommand(order, "Plato 2"));            Kitchen kitchen = new Kitchen();

            waiter.takeOrder(new AddItemCommand(order, "Plato 3"));

            // When

            // Cliente cancela toda la orden            kitchen.prepareDish("");

            order.cancelOrder();            kitchen.prepareDish("   ");

            kitchen.prepareDish("\n\t");

            // Then

            assertThat(order.getItems()).isEmpty();            // Then: Acepta cualquier string (sin validación)

        }            assertThat(kitchen.getCurrentDish()).isEqualTo("\n\t");

        }

        @Test

        @DisplayName("Escenario 6: Múltiples undos consecutivos")        @Test

        void scenario6_MultipleConsecutiveUndos() {        @DisplayName("Secuencia de servir sin preparar no debe causar error")

            // Given        void serveWithoutPrepareSequenceShouldNotCauseError() {

            Waiter waiter = new Waiter();            // Given

            KitchenOrder order = new KitchenOrder();            Kitchen kitchen = new Kitchen();

            KitchenInvoker invoker = new KitchenInvoker();

            // When: Cliente ordena 5 items

            waiter.takeOrder(new AddItemCommand(order, "Item 1"));            // When: Intentar servir sin preparar

            waiter.takeOrder(new AddItemCommand(order, "Item 2"));            invoker.setCommand(new ServeDishCommand(kitchen));

            waiter.takeOrder(new AddItemCommand(order, "Item 3"));            invoker.executeCommand();

            waiter.takeOrder(new AddItemCommand(order, "Item 4"));            invoker.executeCommand();

            waiter.takeOrder(new AddItemCommand(order, "Item 5"));            invoker.executeCommand();



            // Cliente deshace últimos 3            // Then: No hay error, cocina sigue vacía

            waiter.undoLastOrder();            assertThat(kitchen.getCurrentDish()).isNull();

            waiter.undoLastOrder();        }

            waiter.undoLastOrder();    }

}

            // Then
            assertThat(order.getItems()).containsExactly("Item 1", "Item 2");
        }
    }

    @Nested
    @DisplayName("Tests de Encapsulación")
    class EncapsulationTests {

        @Test
        @DisplayName("Comandos deben ser objetos independientes")
        void commandsShouldBeIndependentObjects() {
            // Given
            KitchenOrder order1 = new KitchenOrder();
            KitchenOrder order2 = new KitchenOrder();

            Command cmd1 = new AddItemCommand(order1, "Orden 1");
            Command cmd2 = new AddItemCommand(order2, "Orden 2");

            // When
            cmd1.execute();
            cmd2.execute();

            // Then: No hay interferencia
            assertThat(order1.getItems()).containsExactly("Orden 1");
            assertThat(order2.getItems()).containsExactly("Orden 2");
        }

        @Test
        @DisplayName("Mismo comando puede ejecutarse múltiples veces")
        void sameCommandCanBeExecutedMultipleTimes() {
            // Given
            KitchenOrder order = new KitchenOrder();
            Command command = new AddItemCommand(order, "Pizza");

            // When
            command.execute();
            command.execute();
            command.execute();

            // Then
            assertThat(order.getItems()).hasSize(3);
            assertThat(order.getItems()).containsOnly("Pizza");
        }

        @Test
        @DisplayName("Waiter desacopla cliente de KitchenOrder")
        void waiterDecouple sClientFromOrder() {
            // Given
            Waiter waiter = new Waiter();
            KitchenOrder order = new KitchenOrder();

            // When: Cliente no interactúa directamente con orden
            Command command = new AddItemCommand(order, "Plato");
            waiter.takeOrder(command);

            // Then
            assertThat(order.getItems()).containsExactly("Plato");
        }
    }

    @Nested
    @DisplayName("Tests de Edge Cases")
    class EdgeCaseTests {

        @Test
        @DisplayName("Debe manejar items con nombres null")
        void shouldHandleNullItemNames() {
            // Given
            KitchenOrder order = new KitchenOrder();
            Command command = new AddItemCommand(order, null);

            // When
            command.execute();

            // Then
            assertThat(order.getItems()).containsExactly((String) null);
        }

        @Test
        @DisplayName("Debe manejar items con nombres vacíos")
        void shouldHandleEmptyItemNames() {
            // Given
            KitchenOrder order = new KitchenOrder();

            // When
            order.addItem("");
            order.addItem("   ");

            // Then
            assertThat(order.getItems()).contains("", "   ");
        }

        @Test
        @DisplayName("Undo sin comandos no debe causar error")
        void undoWithoutCommandsShouldNotCauseError() {
            // Given
            Waiter waiter = new Waiter();

            // When
            waiter.undoLastOrder();
            waiter.undoLastOrder();
            waiter.undoLastOrder();

            // Then: No lanza excepción
        }

        @Test
        @DisplayName("Remover de orden vacía no debe causar error")
        void removeFromEmptyOrderShouldNotCauseError() {
            // Given
            KitchenOrder order = new KitchenOrder();

            // When
            order.removeItem("Item Inexistente");

            // Then
            assertThat(order.getItems()).isEmpty();
        }
    }
}
