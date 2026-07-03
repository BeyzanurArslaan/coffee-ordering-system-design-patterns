package cafe.domain.pricing;

import cafe.domain.order.DrinkOrder;

// Strategy: discount for loyalty.
public class LoyaltyStrategy implements PricingStrategy {
    @Override
    public double applyPrice(DrinkOrder order) {
        return order.getTotalCost() * 0.9;
    }

    @Override
    public String getName() {
        return "Loyalty";
    }
}
