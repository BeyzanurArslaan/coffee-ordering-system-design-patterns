package cafe.domain.order;

// Concrete Decorator for extra shot; adds caffeine and cost.
public class ExtraShotDecorator extends BeverageDecorator {
    public ExtraShotDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + extra shot";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.8;
    }
}
