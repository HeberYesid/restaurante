package com.example.patterns.behavioral.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Patrón State - Tests de Cobertura Completa")
class StatePatternTest {

    @Nested
    @DisplayName("TableContext - Contexto de Mesa")
    class TableContextTests {

        private TableContext table;

        @BeforeEach
        void setUp() {
            table = new TableContext(1);
        }

        @Test
        @DisplayName("Debe crear mesa en estado DISPONIBLE")
        void shouldCreateTableInAvailableState() {
            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
            assertThat(table.getTableNumber()).isEqualTo(1);
        }

        @Test
        @DisplayName("Debe permitir reservar mesa disponible")
        void shouldAllowReservingAvailableTable() {
            // When
            table.reserve();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");
        }

        @Test
        @DisplayName("Debe permitir ocupar mesa disponible directamente")
        void shouldAllowOccupyingAvailableTable() {
            // When
            table.occupy();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Debe cambiar de RESERVADA a OCUPADA cuando llega el cliente")
        void shouldChangeFromReservedToOccupied() {
            // Given
            table.reserve();
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");

            // When
            table.occupy();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Debe cambiar de OCUPADA a SUCIA cuando clientes se van")
        void shouldChangeFromOccupiedToDirty() {
            // Given
            table.occupy();
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When
            table.free();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("Debe cambiar de SUCIA a DISPONIBLE después de limpiar")
        void shouldChangeFromDirtyToAvailable() {
            // Given
            table.occupy();
            table.free();
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");

            // When
            table.clean();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Debe cancelar reservación y volver a DISPONIBLE")
        void shouldCancelReservationAndBecomeAvailable() {
            // Given
            table.reserve();
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");

            // When
            table.free();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Ciclo completo: DISPONIBLE → RESERVADA → OCUPADA → SUCIA → DISPONIBLE")
        void shouldCompleteFullCycle() {
            // Given: DISPONIBLE
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");

            // When: Reservar
            table.reserve();
            // Then
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");

            // When: Cliente llega
            table.occupy();
            // Then
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When: Cliente se va
            table.free();
            // Then
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");

            // When: Limpiar
            table.clean();
            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Múltiples mesas con números diferentes")
        void shouldHandleMultipleTables() {
            // Given
            TableContext table1 = new TableContext(1);
            TableContext table2 = new TableContext(2);
            TableContext table3 = new TableContext(3);

            // When
            table1.reserve();
            table2.occupy();
            // table3 queda disponible

            // Then
            assertThat(table1.getCurrentState()).isEqualTo("RESERVADA");
            assertThat(table2.getCurrentState()).isEqualTo("OCUPADA");
            assertThat(table3.getCurrentState()).isEqualTo("DISPONIBLE");
        }
    }

    @Nested
    @DisplayName("AvailableState - Estado Disponible")
    class AvailableStateTests {

        private TableContext table;

        @BeforeEach
        void setUp() {
            table = new TableContext(10);
        }

        @Test
        @DisplayName("Debe permitir reservar desde estado DISPONIBLE")
        void shouldAllowReserveFromAvailable() {
            // When
            table.reserve();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");
        }

        @Test
        @DisplayName("Debe permitir ocupar directamente desde DISPONIBLE")
        void shouldAllowOccupyFromAvailable() {
            // When
            table.occupy();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Llamar free() en mesa DISPONIBLE no cambia el estado")
        void shouldNotChangeStateWhenFreeingAvailableTable() {
            // When
            table.free();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Llamar clean() en mesa DISPONIBLE no cambia el estado")
        void shouldNotChangeStateWhenCleaningAvailableTable() {
            // When
            table.clean();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }
    }

    @Nested
    @DisplayName("ReservedState - Estado Reservada")
    class ReservedStateTests {

        private TableContext table;

        @BeforeEach
        void setUp() {
            table = new TableContext(20);
            table.reserve(); // Poner en estado RESERVADA
        }

        @Test
        @DisplayName("No debe permitir reservar mesa ya reservada")
        void shouldNotAllowReservingAlreadyReservedTable() {
            // Given
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");

            // When
            table.reserve();

            // Then - sigue en RESERVADA
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");
        }

        @Test
        @DisplayName("Debe permitir ocupar mesa RESERVADA cuando llega el cliente")
        void shouldAllowOccupyingReservedTable() {
            // When
            table.occupy();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Debe permitir cancelar reservación (free) y volver a DISPONIBLE")
        void shouldAllowCancelingReservation() {
            // When
            table.free();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("No debe permitir limpiar mesa RESERVADA")
        void shouldNotAllowCleaningReservedTable() {
            // When
            table.clean();

            // Then - sigue en RESERVADA
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");
        }
    }

    @Nested
    @DisplayName("OccupiedState - Estado Ocupada")
    class OccupiedStateTests {

        private TableContext table;

        @BeforeEach
        void setUp() {
            table = new TableContext(30);
            table.occupy(); // Poner en estado OCUPADA
        }

        @Test
        @DisplayName("No debe permitir reservar mesa OCUPADA")
        void shouldNotAllowReservingOccupiedTable() {
            // Given
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When
            table.reserve();

            // Then - sigue en OCUPADA
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("No debe permitir ocupar mesa ya OCUPADA")
        void shouldNotAllowOccupyingAlreadyOccupiedTable() {
            // When
            table.occupy();

            // Then - sigue en OCUPADA
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Debe cambiar a SUCIA cuando los clientes se van (free)")
        void shouldChangeToDirtyWhenCustomersLeave() {
            // When
            table.free();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("No debe permitir limpiar mesa OCUPADA")
        void shouldNotAllowCleaningOccupiedTable() {
            // When
            table.clean();

            // Then - sigue en OCUPADA
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }
    }

    @Nested
    @DisplayName("DirtyState - Estado Sucia")
    class DirtyStateTests {

        private TableContext table;

        @BeforeEach
        void setUp() {
            table = new TableContext(40);
            table.occupy(); // DISPONIBLE → OCUPADA
            table.free();   // OCUPADA → SUCIA
        }

        @Test
        @DisplayName("No debe permitir reservar mesa SUCIA")
        void shouldNotAllowReservingDirtyTable() {
            // Given
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");

            // When
            table.reserve();

            // Then - sigue en SUCIA
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("No debe permitir ocupar mesa SUCIA")
        void shouldNotAllowOccupyingDirtyTable() {
            // When
            table.occupy();

            // Then - sigue en SUCIA
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("Llamar free() en mesa SUCIA no cambia el estado")
        void shouldNotChangeStateWhenFreeingDirtyTable() {
            // When
            table.free();

            // Then - sigue en SUCIA
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("Debe cambiar a DISPONIBLE después de limpiar")
        void shouldChangeToAvailableAfterCleaning() {
            // When
            table.clean();

            // Then
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Mesa SUCIA puede limpiarse y volver al ciclo normal")
        void shouldAllowReturningToNormalCycleAfterCleaning() {
            // When: Limpiar
            table.clean();
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");

            // Then: Puede reservarse nuevamente
            table.reserve();
            assertThat(table.getCurrentState()).isEqualTo("RESERVADA");

            // And: Cliente llega
            table.occupy();
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }
    }

    @Nested
    @DisplayName("Escenarios de Negocio Realistas")
    class BusinessScenariosTests {

        @Test
        @DisplayName("Escenario 1: Cliente con reserva llega y consume")
        void scenario1_ReservationFlow() {
            // Given: Mesa reservada
            TableContext table = new TableContext(101);
            table.reserve();

            // When: Cliente llega
            table.occupy();

            // Then: Mesa ocupada
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When: Cliente termina y se va
            table.free();

            // Then: Mesa necesita limpieza
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");

            // When: Personal limpia
            table.clean();

            // Then: Mesa disponible para siguiente cliente
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Escenario 2: Cliente walk-in (sin reserva)")
        void scenario2_WalkInCustomer() {
            // Given: Mesa disponible
            TableContext table = new TableContext(102);

            // When: Cliente entra sin reserva
            table.occupy();

            // Then: Mesa ocupada directamente
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When: Cliente termina
            table.free();

            // Then: Mesa sucia
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }

        @Test
        @DisplayName("Escenario 3: Cancelación de reserva")
        void scenario3_ReservationCancellation() {
            // Given: Mesa reservada
            TableContext table = new TableContext(103);
            table.reserve();

            // When: Cliente cancela
            table.free();

            // Then: Mesa vuelve a estar disponible
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");

            // And: Otro cliente puede ocuparla
            table.occupy();
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");
        }

        @Test
        @DisplayName("Escenario 4: Restaurante con múltiples mesas")
        void scenario4_MultipleTablesRestaurant() {
            // Given: 5 mesas del restaurante
            TableContext[] tables = new TableContext[5];
            for (int i = 0; i < 5; i++) {
                tables[i] = new TableContext(i + 1);
            }

            // When: Diferentes clientes
            tables[0].reserve();           // Mesa 1: Reservada
            tables[1].occupy();            // Mesa 2: Ocupada (walk-in)
            tables[2].reserve();           // Mesa 3: Reservada
            tables[2].occupy();            // Mesa 3: Cliente llega
            tables[3].occupy();            // Mesa 4: Ocupada
            tables[3].free();              // Mesa 4: Cliente se va (sucia)
            // Mesa 5: Disponible (sin uso)

            // Then: Verificar estados
            assertThat(tables[0].getCurrentState()).isEqualTo("RESERVADA");
            assertThat(tables[1].getCurrentState()).isEqualTo("OCUPADA");
            assertThat(tables[2].getCurrentState()).isEqualTo("OCUPADA");
            assertThat(tables[3].getCurrentState()).isEqualTo("SUCIA");
            assertThat(tables[4].getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Escenario 5: Turno completo de restaurante")
        void scenario5_CompleteRestaurantShift() {
            // Given: Mesa al inicio del turno
            TableContext table = new TableContext(200);

            // Primer servicio
            table.reserve();
            table.occupy();
            table.free();
            table.clean();

            // Segundo servicio
            table.occupy(); // Walk-in
            table.free();
            table.clean();

            // Tercer servicio
            table.reserve();
            table.free(); // Cancelación
            table.occupy(); // Otro cliente toma la mesa
            table.free();
            table.clean();

            // Then: Mesa lista para siguiente turno
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Escenario 6: Intentos inválidos no rompen el estado")
        void scenario6_InvalidAttemptsDoNotBreakState() {
            // Given: Mesa ocupada
            TableContext table = new TableContext(300);
            table.occupy();

            // When: Intentos inválidos
            table.reserve(); // No debería hacer nada
            table.occupy();  // No debería hacer nada
            table.clean();   // No debería hacer nada

            // Then: Sigue ocupada
            assertThat(table.getCurrentState()).isEqualTo("OCUPADA");

            // When: Acción válida
            table.free();

            // Then: Cambia correctamente
            assertThat(table.getCurrentState()).isEqualTo("SUCIA");
        }
    }

    @Nested
    @DisplayName("Tests de Consistencia de Estado")
    class StateConsistencyTests {

        @Test
        @DisplayName("Estado debe ser consistente después de múltiples operaciones")
        void shouldMaintainStateConsistency() {
            TableContext table = new TableContext(500);

            // Secuencia compleja
            table.reserve();
            table.reserve(); // Intento duplicado
            table.occupy();
            table.occupy(); // Intento duplicado
            table.clean();  // Acción inválida
            table.free();
            table.free();   // Intento duplicado
            table.reserve(); // Acción inválida
            table.clean();

            // Debe estar DISPONIBLE
            assertThat(table.getCurrentState()).isEqualTo("DISPONIBLE");
        }

        @Test
        @DisplayName("Número de mesa debe mantenerse constante")
        void shouldMaintainTableNumberConstant() {
            TableContext table = new TableContext(999);

            // Cambiar estados múltiples veces
            table.reserve();
            table.occupy();
            table.free();
            table.clean();
            table.occupy();
            table.free();
            table.clean();

            // Número debe ser el mismo
            assertThat(table.getTableNumber()).isEqualTo(999);
        }
    }
}
