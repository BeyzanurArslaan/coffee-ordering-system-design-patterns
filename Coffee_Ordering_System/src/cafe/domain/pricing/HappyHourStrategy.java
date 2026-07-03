package cafe.domain.pricing;

import cafe.domain.order.DrinkOrder;

// Strategy: discount for happy hour.
public class HappyHourStrategy implements PricingStrategy {
    @Override
    public double applyPrice(DrinkOrder order) {
        return order.getTotalCost() * 0.8;
    }

    @Override
    public String getName() {
        return "Happy Hour";
    }
}
