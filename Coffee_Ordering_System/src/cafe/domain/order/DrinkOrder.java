package cafe.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Order aggregate built by Builder and priced via Strategy.
public class DrinkOrder {
    private final List<Beverage> beverages;

    public DrinkOrder(List<Beverage> beverages) {
        this.beverages = new ArrayList<>(beverages);
    }

    public List<Beverage> getBeverages() {
        return Collections.unmodifiableList(beverages);
    }

    public double getTotalCost() {
        return beverages.stream().mapToDouble(Beverage::getCost).sum();
    }
}
