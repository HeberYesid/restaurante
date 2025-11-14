package com.example.patterns.structural.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Decorator Pattern Tests")
class DecoratorPatternTest {

    @Nested
    @DisplayName("Base Beverage Tests")
    class BaseBeverageTests {

        @Test
        @DisplayName("Coffee should have correct description and cost")
        void coffeeShouldHaveCorrectDescriptionAndCost() {
            Beverage coffee = new Coffee();

            assertThat(coffee.getDescription()).isEqualTo("Café");
            assertThat(coffee.getCost()).isEqualTo(3.50);
        }

        @Test
        @DisplayName("Tea should have correct description and cost")
        void teaShouldHaveCorrectDescriptionAndCost() {
            Beverage tea = new Tea();

            assertThat(tea.getDescription()).isEqualTo("Té");
            assertThat(tea.getCost()).isEqualTo(2.50);
        }

        @Test
        @DisplayName("Juice should have correct description and cost")
        void juiceShouldHaveCorrectDescriptionAndCost() {
            Beverage juice = new Juice();

            assertThat(juice.getDescription()).isEqualTo("Jugo natural");
            assertThat(juice.getCost()).isEqualTo(4.00);
        }
    }

    @Nested
    @DisplayName("MilkDecorator Tests")
    class MilkDecoratorTests {

        @Test
        @DisplayName("Should add milk to coffee")
        void shouldAddMilkToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new MilkDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Café + Leche");
            assertThat(beverage.getCost()).isCloseTo(4.25, within(0.01));
        }

        @Test
        @DisplayName("Should add milk to tea")
        void shouldAddMilkToTea() {
            Beverage beverage = new Tea();
            beverage = new MilkDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Té + Leche");
            assertThat(beverage.getCost()).isCloseTo(3.25, within(0.01));
        }

        @Test
        @DisplayName("Should add milk to juice")
        void shouldAddMilkToJuice() {
            Beverage beverage = new Juice();
            beverage = new MilkDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Jugo natural + Leche");
            assertThat(beverage.getCost()).isCloseTo(4.75, within(0.01));
        }
    }

    @Nested
    @DisplayName("SugarDecorator Tests")
    class SugarDecoratorTests {

        @Test
        @DisplayName("Should add sugar to coffee")
        void shouldAddSugarToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new SugarDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Café + Azúcar");
            assertThat(beverage.getCost()).isCloseTo(3.75, within(0.01));
        }

        @Test
        @DisplayName("Should add sugar to tea")
        void shouldAddSugarToTea() {
            Beverage beverage = new Tea();
            beverage = new SugarDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Té + Azúcar");
            assertThat(beverage.getCost()).isCloseTo(2.75, within(0.01));
        }

        @Test
        @DisplayName("Should add sugar to juice")
        void shouldAddSugarToJuice() {
            Beverage beverage = new Juice();
            beverage = new SugarDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Jugo natural + Azúcar");
            assertThat(beverage.getCost()).isCloseTo(4.25, within(0.01));
        }
    }

    @Nested
    @DisplayName("WhippedCreamDecorator Tests")
    class WhippedCreamDecoratorTests {

        @Test
        @DisplayName("Should add whipped cream to coffee")
        void shouldAddWhippedCreamToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new WhippedCreamDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Café + Crema batida");
            assertThat(beverage.getCost()).isCloseTo(4.50, within(0.01));
        }

        @Test
        @DisplayName("Should add whipped cream to tea")
        void shouldAddWhippedCreamToTea() {
            Beverage beverage = new Tea();
            beverage = new WhippedCreamDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Té + Crema batida");
            assertThat(beverage.getCost()).isCloseTo(3.50, within(0.01));
        }

        @Test
        @DisplayName("Should add whipped cream to juice")
        void shouldAddWhippedCreamToJuice() {
            Beverage beverage = new Juice();
            beverage = new WhippedCreamDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Jugo natural + Crema batida");
            assertThat(beverage.getCost()).isCloseTo(5.00, within(0.01));
        }
    }

    @Nested
    @DisplayName("CaramelDecorator Tests")
    class CaramelDecoratorTests {

        @Test
        @DisplayName("Should add caramel to coffee")
        void shouldAddCaramelToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new CaramelDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Café + Caramelo");
            assertThat(beverage.getCost()).isCloseTo(4.30, within(0.01));
        }

        @Test
        @DisplayName("Should add caramel to tea")
        void shouldAddCaramelToTea() {
            Beverage beverage = new Tea();
            beverage = new CaramelDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Té + Caramelo");
            assertThat(beverage.getCost()).isCloseTo(3.30, within(0.01));
        }

        @Test
        @DisplayName("Should add caramel to juice")
        void shouldAddCaramelToJuice() {
            Beverage beverage = new Juice();
            beverage = new CaramelDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Jugo natural + Caramelo");
            assertThat(beverage.getCost()).isCloseTo(4.80, within(0.01));
        }
    }

    @Nested
    @DisplayName("Multiple Decorator Tests")
    class MultipleDecoratorTests {

        @Test
        @DisplayName("Should add milk and sugar to coffee")
        void shouldAddMilkAndSugarToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new MilkDecorator(beverage);
            beverage = new SugarDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Café + Leche + Azúcar");
            assertThat(beverage.getCost()).isCloseTo(4.50, within(0.01));
        }

        @Test
        @DisplayName("Should add all decorators to coffee")
        void shouldAddAllDecoratorsToCoffee() {
            Beverage beverage = new Coffee();
            beverage = new MilkDecorator(beverage);
            beverage = new SugarDecorator(beverage);
            beverage = new WhippedCreamDecorator(beverage);
            beverage = new CaramelDecorator(beverage);

            assertThat(beverage.getDescription())
                .isEqualTo("Café + Leche + Azúcar + Crema batida + Caramelo");
            assertThat(beverage.getCost()).isCloseTo(6.30, within(0.01));
        }

        @Test
        @DisplayName("Should add multiple decorators to tea")
        void shouldAddMultipleDecoratorsToTea() {
            Beverage beverage = new Tea();
            beverage = new SugarDecorator(beverage);
            beverage = new MilkDecorator(beverage);

            assertThat(beverage.getDescription()).isEqualTo("Té + Azúcar + Leche");
            assertThat(beverage.getCost()).isCloseTo(3.50, within(0.01));
        }

        @Test
        @DisplayName("Should add whipped cream and caramel to juice")
        void shouldAddWhippedCreamAndCaramelToJuice() {
            Beverage beverage = new Juice();
            beverage = new WhippedCreamDecorator(beverage);
            beverage = new CaramelDecorator(beverage);

            assertThat(beverage.getDescription())
                .isEqualTo("Jugo natural + Crema batida + Caramelo");
            assertThat(beverage.getCost()).isCloseTo(5.80, within(0.01));
        }

        @Test
        @DisplayName("Should add same decorator multiple times")
        void shouldAddSameDecoratorMultipleTimes() {
            Beverage beverage = new Coffee();
            beverage = new SugarDecorator(beverage);
            beverage = new SugarDecorator(beverage);
            beverage = new SugarDecorator(beverage);

            assertThat(beverage.getDescription())
                .isEqualTo("Café + Azúcar + Azúcar + Azúcar");
            assertThat(beverage.getCost()).isCloseTo(4.25, within(0.01));
        }
    }

    @Nested
    @DisplayName("Complex Decorator Combinations")
    class ComplexDecoratorCombinations {

        @Test
        @DisplayName("Should create latte with sugar")
        void shouldCreateLatteWithSugar() {
            Beverage latte = new Coffee();
            latte = new MilkDecorator(latte);
            latte = new SugarDecorator(latte);

            assertThat(latte.getDescription()).contains("Café", "Leche", "Azúcar");
            assertThat(latte.getCost()).isCloseTo(4.50, within(0.01));
        }

        @Test
        @DisplayName("Should create caramel macchiato")
        void shouldCreateCaramelMacchiato() {
            Beverage macchiato = new Coffee();
            macchiato = new MilkDecorator(macchiato);
            macchiato = new CaramelDecorator(macchiato);
            macchiato = new WhippedCreamDecorator(macchiato);

            assertThat(macchiato.getDescription())
                .contains("Café", "Leche", "Caramelo", "Crema batida");
            assertThat(macchiato.getCost()).isCloseTo(6.05, within(0.01));
        }

        @Test
        @DisplayName("Should create sweet tea")
        void shouldCreateSweetTea() {
            Beverage sweetTea = new Tea();
            sweetTea = new SugarDecorator(sweetTea);
            sweetTea = new SugarDecorator(sweetTea);

            assertThat(sweetTea.getDescription()).isEqualTo("Té + Azúcar + Azúcar");
            assertThat(sweetTea.getCost()).isCloseTo(3.00, within(0.01));
        }

        @Test
        @DisplayName("Should create fancy juice")
        void shouldCreateFancyJuice() {
            Beverage fancyJuice = new Juice();
            fancyJuice = new WhippedCreamDecorator(fancyJuice);
            fancyJuice = new CaramelDecorator(fancyJuice);
            fancyJuice = new SugarDecorator(fancyJuice);

            assertThat(fancyJuice.getCost()).isCloseTo(6.05, within(0.01));
        }

        @Test
        @DisplayName("Should handle extreme decoration")
        void shouldHandleExtremeDecoration() {
            Beverage extreme = new Coffee();
            extreme = new MilkDecorator(extreme);
            extreme = new MilkDecorator(extreme);
            extreme = new SugarDecorator(extreme);
            extreme = new WhippedCreamDecorator(extreme);
            extreme = new CaramelDecorator(extreme);
            extreme = new CaramelDecorator(extreme);

            assertThat(extreme.getCost()).isCloseTo(7.85, within(0.01));
        }
    }

    @Nested
    @DisplayName("Decorator Order Tests")
    class DecoratorOrderTests {

        @Test
        @DisplayName("Should produce same cost regardless of decoration order")
        void shouldProduceSameCostRegardlessOfOrder() {
            Beverage beverage1 = new Coffee();
            beverage1 = new MilkDecorator(beverage1);
            beverage1 = new SugarDecorator(beverage1);

            Beverage beverage2 = new Coffee();
            beverage2 = new SugarDecorator(beverage2);
            beverage2 = new MilkDecorator(beverage2);

            assertThat(beverage1.getCost()).isEqualTo(beverage2.getCost());
        }

        @Test
        @DisplayName("Should produce different descriptions based on decoration order")
        void shouldProduceDifferentDescriptionsBasedOnOrder() {
            Beverage beverage1 = new Coffee();
            beverage1 = new MilkDecorator(beverage1);
            beverage1 = new SugarDecorator(beverage1);

            Beverage beverage2 = new Coffee();
            beverage2 = new SugarDecorator(beverage2);
            beverage2 = new MilkDecorator(beverage2);

            assertThat(beverage1.getDescription()).isEqualTo("Café + Leche + Azúcar");
            assertThat(beverage2.getDescription()).isEqualTo("Café + Azúcar + Leche");
        }

        @Test
        @DisplayName("Should maintain decoration chain integrity")
        void shouldMaintainDecorationChainIntegrity() {
            Beverage base = new Coffee();
            Beverage decorated1 = new MilkDecorator(base);
            Beverage decorated2 = new SugarDecorator(decorated1);
            Beverage decorated3 = new WhippedCreamDecorator(decorated2);

            assertThat(base.getCost()).isEqualTo(3.50);
            assertThat(decorated1.getCost()).isCloseTo(4.25, within(0.01));
            assertThat(decorated2.getCost()).isCloseTo(4.50, within(0.01));
            assertThat(decorated3.getCost()).isCloseTo(5.50, within(0.01));
        }
    }
}
