package cafe.domain.order;

// Decorator base that wraps a drink.
public abstract class BeverageDecorator implements Beverage {
    protected final Beverage beverage;

    protected BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
