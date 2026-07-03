package cafe.domain.validation;

import cafe.domain.validation.OrderContext;

// Chain of Responsibility: inventory check handler.
public class InventoryHandler extends OrderHandler {
    @Override
    protected boolean check(OrderContext context) {
        boolean ok = !context.getOrder().getBeverages().isEmpty();
        if (!ok) {
            System.out.println("InventoryHandler: order is empty");
        }
        return ok;
    }
}
