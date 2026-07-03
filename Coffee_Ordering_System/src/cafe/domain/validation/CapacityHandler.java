package cafe.domain.validation;

import cafe.domain.validation.OrderContext;

// Chain of Responsibility: capacity check handler.
public class CapacityHandler extends OrderHandler {
    private final int maxCapacity;

    public CapacityHandler(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean check(OrderContext context) {
        boolean ok = context.getPendingOrders() < maxCapacity;
        if (!ok) {
            System.out.println("CapacityHandler: too many pending orders");
        }
        return ok;
    }
}
