package cafe.domain.pricing;

import cafe.domain.order.DrinkOrder;

// Strategy for pricing rule.
public interface PricingStrategy {
    double applyPrice(DrinkOrder order);
    String getName();
}
