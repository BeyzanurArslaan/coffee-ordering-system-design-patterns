package cafe.domain.pricing;

import cafe.domain.order.DrinkOrder;

// Strategy: normal price.
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double applyPrice(DrinkOrder order) {
        return order.getTotalCost();
    }

    @Override
    public String getName() {
        return "Standard";
    }
}
