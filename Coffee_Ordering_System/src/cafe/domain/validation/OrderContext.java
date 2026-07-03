package cafe.domain.validation;

import cafe.domain.order.DrinkOrder;
import cafe.domain.payment.PaymentType;

// Data passed through Chain of Responsibility.
public class OrderContext {
    private final DrinkOrder order;
    private final PaymentType paymentType;
    private final int pendingOrders;

    public OrderContext(DrinkOrder order, PaymentType paymentType, int pendingOrders) {
        this.order = order;
        this.paymentType = paymentType;
        this.pendingOrders = pendingOrders;
    }

    public DrinkOrder getOrder() {
        return order;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }
}
