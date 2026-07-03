package cafe.domain.order;

// Plain drink before Decorators.
public class BaseBeverage implements Beverage {
    private final String description;
    private final double cost;

    public BaseBeverage(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
