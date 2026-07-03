package cafe.domain.order;

// Concrete Decorator for syrup.
public class SyrupDecorator extends BeverageDecorator {
    private final String flavor;

    public SyrupDecorator(Beverage beverage, String flavor) {
        super(beverage);
        this.flavor = flavor;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + " + flavor + " syrup";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.5;
    }
}
