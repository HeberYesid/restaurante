package com.example.patterns.behavioral.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Strategy Pattern Tests")
class StrategyPatternTest {

    @Nested
    @DisplayName("MenuItem Tests")
    class MenuItemTests {

        @Test
        @DisplayName("Should create menu item with default regular pricing")
        void shouldCreateMenuItemWithDefaultRegularPricing() {
            MenuItem item = new MenuItem("Burger", 10.0);

            assertThat(item.getName()).isEqualTo("Burger");
            assertThat(item.getBasePrice()).isEqualTo(10.0);
            assertThat(item.getPrice()).isEqualTo(10.0);
        }

        @Test
        @DisplayName("Should change pricing strategy")
        void shouldChangePricingStrategy() {
            MenuItem item = new MenuItem("Pizza", 15.0);
            
            item.setPricingStrategy(new HappyHourPricing());
            
            assertThat(item.getPrice()).isCloseTo(10.5, within(0.01));
        }

        @Test
        @DisplayName("Should return correct price info")
        void shouldReturnCorrectPriceInfo() {
            MenuItem item = new MenuItem("Pasta", 12.0);
            
            String priceInfo = item.getPriceInfo();
            
            assertThat(priceInfo).contains("Pasta", "12.00", "Precio Regular");
        }

        @Test
        @DisplayName("Should handle multiple strategy changes")
        void shouldHandleMultipleStrategyChanges() {
            MenuItem item = new MenuItem("Salad", 8.0);
            
            item.setPricingStrategy(new RegularPricing());
            assertThat(item.getPrice()).isEqualTo(8.0);
            
            item.setPricingStrategy(new MemberPricing());
            assertThat(item.getPrice()).isCloseTo(6.8, within(0.01));
            
            item.setPricingStrategy(new WeekendPricing());
            assertThat(item.getPrice()).isCloseTo(9.2, within(0.01));
        }

        @Test
        @DisplayName("Should create items with different base prices")
        void shouldCreateItemsWithDifferentBasePrices() {
            MenuItem cheap = new MenuItem("Water", 1.0);
            MenuItem expensive = new MenuItem("Lobster", 50.0);
            
            assertThat(cheap.getBasePrice()).isEqualTo(1.0);
            assertThat(expensive.getBasePrice()).isEqualTo(50.0);
        }
    }

    @Nested
    @DisplayName("RegularPricing Tests")
    class RegularPricingTests {

        private PricingStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new RegularPricing();
        }

        @Test
        @DisplayName("Should return base price unchanged")
        void shouldReturnBasePriceUnchanged() {
            double price = strategy.calculatePrice(20.0);
            
            assertThat(price).isEqualTo(20.0);
        }

        @Test
        @DisplayName("Should have correct strategy name")
        void shouldHaveCorrectStrategyName() {
            assertThat(strategy.getStrategyName()).isEqualTo("Precio Regular");
        }

        @Test
        @DisplayName("Should handle various base prices")
        void shouldHandleVariousBasePrices() {
            assertThat(strategy.calculatePrice(5.0)).isEqualTo(5.0);
            assertThat(strategy.calculatePrice(10.0)).isEqualTo(10.0);
            assertThat(strategy.calculatePrice(100.0)).isEqualTo(100.0);
        }

        @Test
        @DisplayName("Should handle zero price")
        void shouldHandleZeroPrice() {
            assertThat(strategy.calculatePrice(0.0)).isEqualTo(0.0);
        }
    }

    @Nested
    @DisplayName("HappyHourPricing Tests")
    class HappyHourPricingTests {

        private PricingStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new HappyHourPricing();
        }

        @Test
        @DisplayName("Should apply 30% discount")
        void shouldApply30PercentDiscount() {
            double price = strategy.calculatePrice(10.0);
            
            assertThat(price).isCloseTo(7.0, within(0.01));
        }

        @Test
        @DisplayName("Should have correct strategy name")
        void shouldHaveCorrectStrategyName() {
            assertThat(strategy.getStrategyName())
                .isEqualTo("Happy Hour (30% descuento)");
        }

        @Test
        @DisplayName("Should calculate discount for various prices")
        void shouldCalculateDiscountForVariousPrices() {
            assertThat(strategy.calculatePrice(20.0)).isCloseTo(14.0, within(0.01));
            assertThat(strategy.calculatePrice(50.0)).isCloseTo(35.0, within(0.01));
            assertThat(strategy.calculatePrice(100.0)).isCloseTo(70.0, within(0.01));
        }

        @Test
        @DisplayName("Should handle small prices")
        void shouldHandleSmallPrices() {
            assertThat(strategy.calculatePrice(1.0)).isCloseTo(0.7, within(0.01));
            assertThat(strategy.calculatePrice(2.5)).isCloseTo(1.75, within(0.01));
        }
    }

    @Nested
    @DisplayName("MemberPricing Tests")
    class MemberPricingTests {

        private PricingStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new MemberPricing();
        }

        @Test
        @DisplayName("Should apply 15% discount")
        void shouldApply15PercentDiscount() {
            double price = strategy.calculatePrice(20.0);
            
            assertThat(price).isCloseTo(17.0, within(0.01));
        }

        @Test
        @DisplayName("Should have correct strategy name")
        void shouldHaveCorrectStrategyName() {
            assertThat(strategy.getStrategyName())
                .isEqualTo("Precio de Miembro (15% descuento)");
        }

        @Test
        @DisplayName("Should calculate member discount for various prices")
        void shouldCalculateMemberDiscountForVariousPrices() {
            assertThat(strategy.calculatePrice(10.0)).isCloseTo(8.5, within(0.01));
            assertThat(strategy.calculatePrice(50.0)).isCloseTo(42.5, within(0.01));
            assertThat(strategy.calculatePrice(100.0)).isCloseTo(85.0, within(0.01));
        }

        @Test
        @DisplayName("Should handle decimal prices")
        void shouldHandleDecimalPrices() {
            assertThat(strategy.calculatePrice(12.50)).isCloseTo(10.625, within(0.01));
            assertThat(strategy.calculatePrice(33.33)).isCloseTo(28.3305, within(0.01));
        }
    }

    @Nested
    @DisplayName("WeekendPricing Tests")
    class WeekendPricingTests {

        private PricingStrategy strategy;

        @BeforeEach
        void setUp() {
            strategy = new WeekendPricing();
        }

        @Test
        @DisplayName("Should apply 15% surcharge")
        void shouldApply15PercentSurcharge() {
            double price = strategy.calculatePrice(20.0);
            
            assertThat(price).isCloseTo(23.0, within(0.01));
        }

        @Test
        @DisplayName("Should have correct strategy name")
        void shouldHaveCorrectStrategyName() {
            assertThat(strategy.getStrategyName())
                .isEqualTo("Fin de Semana (15% recargo)");
        }

        @Test
        @DisplayName("Should calculate weekend surcharge for various prices")
        void shouldCalculateWeekendSurchargeForVariousPrices() {
            assertThat(strategy.calculatePrice(10.0)).isCloseTo(11.5, within(0.01));
            assertThat(strategy.calculatePrice(50.0)).isCloseTo(57.5, within(0.01));
            assertThat(strategy.calculatePrice(100.0)).isCloseTo(115.0, within(0.01));
        }

        @Test
        @DisplayName("Should handle low prices with surcharge")
        void shouldHandleLowPricesWithSurcharge() {
            assertThat(strategy.calculatePrice(5.0)).isCloseTo(5.75, within(0.01));
            assertThat(strategy.calculatePrice(2.0)).isCloseTo(2.3, within(0.01));
        }
    }

    @Nested
    @DisplayName("Strategy Comparison Tests")
    class StrategyComparisonTests {

        @Test
        @DisplayName("Should compare all strategies on same item")
        void shouldCompareAllStrategiesOnSameItem() {
            MenuItem item = new MenuItem("Steak", 30.0);
            
            item.setPricingStrategy(new RegularPricing());
            double regularPrice = item.getPrice();
            
            item.setPricingStrategy(new HappyHourPricing());
            double happyHourPrice = item.getPrice();
            
            item.setPricingStrategy(new MemberPricing());
            double memberPrice = item.getPrice();
            
            item.setPricingStrategy(new WeekendPricing());
            double weekendPrice = item.getPrice();
            
            assertThat(regularPrice).isEqualTo(30.0);
            assertThat(happyHourPrice).isCloseTo(21.0, within(0.01));
            assertThat(memberPrice).isCloseTo(25.5, within(0.01));
            assertThat(weekendPrice).isCloseTo(34.5, within(0.01));
        }

        @Test
        @DisplayName("Happy Hour should be cheapest strategy")
        void happyHourShouldBeCheapestStrategy() {
            double basePrice = 50.0;
            
            double regular = new RegularPricing().calculatePrice(basePrice);
            double happyHour = new HappyHourPricing().calculatePrice(basePrice);
            double member = new MemberPricing().calculatePrice(basePrice);
            double weekend = new WeekendPricing().calculatePrice(basePrice);
            
            assertThat(happyHour).isLessThan(member);
            assertThat(happyHour).isLessThan(regular);
            assertThat(happyHour).isLessThan(weekend);
        }

        @Test
        @DisplayName("Weekend should be most expensive strategy")
        void weekendShouldBeMostExpensiveStrategy() {
            double basePrice = 40.0;
            
            double regular = new RegularPricing().calculatePrice(basePrice);
            double happyHour = new HappyHourPricing().calculatePrice(basePrice);
            double member = new MemberPricing().calculatePrice(basePrice);
            double weekend = new WeekendPricing().calculatePrice(basePrice);
            
            assertThat(weekend).isGreaterThan(regular);
            assertThat(weekend).isGreaterThan(member);
            assertThat(weekend).isGreaterThan(happyHour);
        }

        @Test
        @DisplayName("Should verify strategy name uniqueness")
        void shouldVerifyStrategyNameUniqueness() {
            String regular = new RegularPricing().getStrategyName();
            String happyHour = new HappyHourPricing().getStrategyName();
            String member = new MemberPricing().getStrategyName();
            String weekend = new WeekendPricing().getStrategyName();
            
            assertThat(regular).isNotEqualTo(happyHour);
            assertThat(regular).isNotEqualTo(member);
            assertThat(regular).isNotEqualTo(weekend);
            assertThat(happyHour).isNotEqualTo(member);
            assertThat(happyHour).isNotEqualTo(weekend);
            assertThat(member).isNotEqualTo(weekend);
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should handle menu with multiple items and strategies")
        void shouldHandleMenuWithMultipleItemsAndStrategies() {
            MenuItem burger = new MenuItem("Burger", 12.0);
            MenuItem salad = new MenuItem("Salad", 8.0);
            MenuItem steak = new MenuItem("Steak", 25.0);
            
            burger.setPricingStrategy(new HappyHourPricing());
            salad.setPricingStrategy(new MemberPricing());
            steak.setPricingStrategy(new WeekendPricing());
            
            assertThat(burger.getPrice()).isCloseTo(8.4, within(0.01));
            assertThat(salad.getPrice()).isCloseTo(6.8, within(0.01));
            assertThat(steak.getPrice()).isCloseTo(28.75, within(0.01));
        }

        @Test
        @DisplayName("Should calculate total bill with mixed strategies")
        void shouldCalculateTotalBillWithMixedStrategies() {
            MenuItem[] items = {
                new MenuItem("Item1", 10.0),
                new MenuItem("Item2", 15.0),
                new MenuItem("Item3", 20.0)
            };
            
            items[0].setPricingStrategy(new RegularPricing());
            items[1].setPricingStrategy(new HappyHourPricing());
            items[2].setPricingStrategy(new MemberPricing());
            
            double total = items[0].getPrice() + items[1].getPrice() + items[2].getPrice();
            
            assertThat(total).isCloseTo(37.5, within(0.01));
        }

        @Test
        @DisplayName("Should handle dynamic strategy switching during service")
        void shouldHandleDynamicStrategySwitchingDuringService() {
            MenuItem drink = new MenuItem("Cocktail", 12.0);
            
            // Before happy hour
            drink.setPricingStrategy(new RegularPricing());
            double beforeHappyHour = drink.getPrice();
            
            // During happy hour
            drink.setPricingStrategy(new HappyHourPricing());
            double duringHappyHour = drink.getPrice();
            
            // After happy hour, weekend pricing
            drink.setPricingStrategy(new WeekendPricing());
            double weekend = drink.getPrice();
            
            assertThat(beforeHappyHour).isEqualTo(12.0);
            assertThat(duringHappyHour).isCloseTo(8.4, within(0.01));
            assertThat(weekend).isCloseTo(13.8, within(0.01));
        }

        @Test
        @DisplayName("Should verify price info contains all relevant data")
        void shouldVerifyPriceInfoContainsAllRelevantData() {
            MenuItem item = new MenuItem("Dessert", 7.50);
            item.setPricingStrategy(new MemberPricing());
            
            String info = item.getPriceInfo();
            
            assertThat(info).contains("Dessert");
            assertThat(info).contains("7.50");
            assertThat(info).contains("Miembro");
            assertThat(info).contains("6.3");
        }
    }
}
