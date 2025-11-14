package com.example.patterns.behavioral.chainofresponsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Chain of Responsibility Pattern Tests")
class ChainOfResponsibilityPatternTest {

    private DiscountHandler vipHandler;
    private DiscountHandler loyaltyHandler;
    private DiscountHandler bulkHandler;
    private DiscountHandler defaultHandler;

    @BeforeEach
    void setUp() {
        vipHandler = new VIPDiscountHandler();
        loyaltyHandler = new LoyaltyDiscountHandler();
        bulkHandler = new BulkOrderDiscountHandler();
        defaultHandler = new DefaultDiscountHandler();

        vipHandler.setNext(loyaltyHandler);
        loyaltyHandler.setNext(bulkHandler);
        bulkHandler.setNext(defaultHandler);
    }

    @Nested
    @DisplayName("DiscountRequest Tests")
    class DiscountRequestTests {

        @Test
        @DisplayName("Should create discount request with all fields")
        void shouldCreateDiscountRequestWithAllFields() {
            DiscountRequest request = new DiscountRequest("VIP", 150.0, true, 20);

            assertThat(request.getCustomerType()).isEqualTo("VIP");
            assertThat(request.getOrderAmount()).isEqualTo(150.0);
            assertThat(request.isLoyalCustomer()).isTrue();
            assertThat(request.getVisitCount()).isEqualTo(20);
        }

        @Test
        @DisplayName("Should create regular customer request")
        void shouldCreateRegularCustomerRequest() {
            DiscountRequest request = new DiscountRequest("REGULAR", 50.0, false, 2);

            assertThat(request.getCustomerType()).isEqualTo("REGULAR");
            assertThat(request.getOrderAmount()).isEqualTo(50.0);
            assertThat(request.isLoyalCustomer()).isFalse();
            assertThat(request.getVisitCount()).isEqualTo(2);
        }

        @Test
        @DisplayName("Should create request with zero amount")
        void shouldCreateRequestWithZeroAmount() {
            DiscountRequest request = new DiscountRequest("MEMBER", 0.0, true, 5);

            assertThat(request.getOrderAmount()).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should create request with high visit count")
        void shouldCreateRequestWithHighVisitCount() {
            DiscountRequest request = new DiscountRequest("LOYAL", 75.0, true, 100);

            assertThat(request.getVisitCount()).isEqualTo(100);
        }
    }

    @Nested
    @DisplayName("VIPDiscountHandler Tests")
    class VIPDiscountHandlerTests {

        @Test
        @DisplayName("Should apply 25% discount for VIP customers")
        void shouldApply25PercentDiscountForVIPCustomers() {
            DiscountRequest request = new DiscountRequest("VIP", 100.0, false, 0);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.25);
        }

        @Test
        @DisplayName("Should pass to next handler for non-VIP customers")
        void shouldPassToNextHandlerForNonVIPCustomers() {
            DiscountRequest request = new DiscountRequest("REGULAR", 50.0, false, 0);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isNotEqualTo(0.25);
        }

        @Test
        @DisplayName("Should return zero when no next handler and not VIP")
        void shouldReturnZeroWhenNoNextHandlerAndNotVIP() {
            DiscountHandler isolatedVipHandler = new VIPDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 100.0, false, 0);

            double discount = isolatedVipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should handle VIP request with high order amount")
        void shouldHandleVIPRequestWithHighOrderAmount() {
            DiscountRequest request = new DiscountRequest("VIP", 1000.0, true, 50);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.25);
        }
    }

    @Nested
    @DisplayName("LoyaltyDiscountHandler Tests")
    class LoyaltyDiscountHandlerTests {

        @Test
        @DisplayName("Should apply 15% discount for loyal customers with 10+ visits")
        void shouldApply15PercentDiscountForLoyalCustomers() {
            DiscountHandler handler = new LoyaltyDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 80.0, true, 10);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.15);
        }

        @Test
        @DisplayName("Should not apply discount for loyal customer with less than 10 visits")
        void shouldNotApplyDiscountForLessThan10Visits() {
            DiscountHandler handler = new LoyaltyDiscountHandler();
            handler.setNext(defaultHandler);
            DiscountRequest request = new DiscountRequest("REGULAR", 80.0, true, 9);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isNotEqualTo(0.15);
        }

        @Test
        @DisplayName("Should not apply discount for non-loyal customer")
        void shouldNotApplyDiscountForNonLoyalCustomer() {
            DiscountHandler handler = new LoyaltyDiscountHandler();
            handler.setNext(defaultHandler);
            DiscountRequest request = new DiscountRequest("REGULAR", 80.0, false, 15);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isNotEqualTo(0.15);
        }

        @Test
        @DisplayName("Should apply discount for exactly 10 visits")
        void shouldApplyDiscountForExactly10Visits() {
            DiscountHandler handler = new LoyaltyDiscountHandler();
            DiscountRequest request = new DiscountRequest("MEMBER", 50.0, true, 10);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.15);
        }
    }

    @Nested
    @DisplayName("BulkOrderDiscountHandler Tests")
    class BulkOrderDiscountHandlerTests {

        @Test
        @DisplayName("Should apply 10% discount for orders over $100")
        void shouldApply10PercentDiscountForOrdersOver100() {
            DiscountHandler handler = new BulkOrderDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 150.0, false, 0);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.10);
        }

        @Test
        @DisplayName("Should not apply discount for orders at or below $100")
        void shouldNotApplyDiscountForOrdersAt100OrBelow() {
            DiscountHandler handler = new BulkOrderDiscountHandler();
            handler.setNext(defaultHandler);
            DiscountRequest request = new DiscountRequest("REGULAR", 100.0, false, 0);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isNotEqualTo(0.10);
        }

        @Test
        @DisplayName("Should apply discount for very large orders")
        void shouldApplyDiscountForVeryLargeOrders() {
            DiscountHandler handler = new BulkOrderDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 5000.0, false, 0);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.10);
        }

        @Test
        @DisplayName("Should apply discount for $100.01")
        void shouldApplyDiscountForJustOver100() {
            DiscountHandler handler = new BulkOrderDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 100.01, false, 0);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.10);
        }
    }

    @Nested
    @DisplayName("DefaultDiscountHandler Tests")
    class DefaultDiscountHandlerTests {

        @Test
        @DisplayName("Should always apply 5% discount")
        void shouldAlwaysApply5PercentDiscount() {
            DiscountHandler handler = new DefaultDiscountHandler();
            DiscountRequest request = new DiscountRequest("REGULAR", 50.0, false, 0);

            double discount = handler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.05);
        }

        @Test
        @DisplayName("Should apply default discount regardless of customer type")
        void shouldApplyDefaultDiscountRegardlessOfType() {
            DiscountHandler handler = new DefaultDiscountHandler();
            
            DiscountRequest request1 = new DiscountRequest("VIP", 50.0, false, 0);
            DiscountRequest request2 = new DiscountRequest("REGULAR", 50.0, false, 0);
            DiscountRequest request3 = new DiscountRequest("MEMBER", 50.0, false, 0);

            assertThat(handler.handleDiscount(request1)).isEqualTo(0.05);
            assertThat(handler.handleDiscount(request2)).isEqualTo(0.05);
            assertThat(handler.handleDiscount(request3)).isEqualTo(0.05);
        }

        @Test
        @DisplayName("Should apply default discount for any order amount")
        void shouldApplyDefaultDiscountForAnyAmount() {
            DiscountHandler handler = new DefaultDiscountHandler();
            
            DiscountRequest request1 = new DiscountRequest("REGULAR", 1.0, false, 0);
            DiscountRequest request2 = new DiscountRequest("REGULAR", 1000.0, false, 0);

            assertThat(handler.handleDiscount(request1)).isEqualTo(0.05);
            assertThat(handler.handleDiscount(request2)).isEqualTo(0.05);
        }
    }

    @Nested
    @DisplayName("Chain Integration Tests")
    class ChainIntegrationTests {

        @Test
        @DisplayName("Should apply VIP discount at start of chain")
        void shouldApplyVIPDiscountAtStartOfChain() {
            DiscountRequest request = new DiscountRequest("VIP", 200.0, true, 15);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.25);
        }

        @Test
        @DisplayName("Should pass through VIP to Loyalty handler")
        void shouldPassThroughToLoyaltyHandler() {
            DiscountRequest request = new DiscountRequest("REGULAR", 80.0, true, 12);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.15);
        }

        @Test
        @DisplayName("Should pass through to BulkOrder handler")
        void shouldPassThroughToBulkOrderHandler() {
            DiscountRequest request = new DiscountRequest("REGULAR", 150.0, false, 5);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.10);
        }

        @Test
        @DisplayName("Should reach default handler at end of chain")
        void shouldReachDefaultHandlerAtEndOfChain() {
            DiscountRequest request = new DiscountRequest("REGULAR", 50.0, false, 3);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.05);
        }

        @Test
        @DisplayName("Should handle chain with different starting points")
        void shouldHandleChainWithDifferentStartingPoints() {
            DiscountRequest request = new DiscountRequest("REGULAR", 120.0, false, 2);

            double discountFromVIP = vipHandler.handleDiscount(request);
            double discountFromLoyalty = loyaltyHandler.handleDiscount(request);
            double discountFromBulk = bulkHandler.handleDiscount(request);

            assertThat(discountFromVIP).isEqualTo(0.10);
            assertThat(discountFromLoyalty).isEqualTo(0.10);
            assertThat(discountFromBulk).isEqualTo(0.10);
        }

        @Test
        @DisplayName("Should prioritize VIP over bulk order")
        void shouldPrioritizeVIPOverBulkOrder() {
            DiscountRequest request = new DiscountRequest("VIP", 200.0, false, 0);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.25);
        }

        @Test
        @DisplayName("Should prioritize loyalty over bulk when both apply")
        void shouldPrioritizeLoyaltyOverBulk() {
            DiscountRequest request = new DiscountRequest("MEMBER", 150.0, true, 15);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.15);
        }

        @Test
        @DisplayName("Should handle edge case with all criteria met")
        void shouldHandleEdgeCaseWithAllCriteriaMet() {
            DiscountRequest request = new DiscountRequest("VIP", 250.0, true, 20);

            double discount = vipHandler.handleDiscount(request);

            assertThat(discount).isEqualTo(0.25);
        }
    }
}
