package cafe.domain.order;

// Concrete Decorator for milk add-on; changes text and price.
public class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + milk";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.6;
    }
}
