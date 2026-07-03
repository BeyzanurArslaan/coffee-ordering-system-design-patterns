package cafe.domain.validation;

import cafe.domain.validation.OrderContext;

// Chain of Responsibility: payment type handler.
public class PaymentHandler extends OrderHandler {
    @Override
    protected boolean check(OrderContext context) {
        if (context.getPaymentType() == null) {
            System.out.println("PaymentHandler: missing payment type");
            return false;
        }
        return true;
    }
}
