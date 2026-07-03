package cafe.domain.order;

import java.util.ArrayList;
import java.util.List;

// Concrete Builder for drinks, wrapping add-ons with Decorators.
public class DrinkOrderBuilder implements OrderBuilder {
    private final List<Beverage> beverages = new ArrayList<>();
    private Beverage current;

    @Override
    public void startBeverage(String name, double price) {
        current = new BaseBeverage(name, price);
    }

    @Override
    public void addMilk() {
        if (current != null) {
            current = new MilkDecorator(current);
        }
    }

    @Override
    public void addExtraShot() {
        if (current != null) {
            current = new ExtraShotDecorator(current);
        }
    }

    @Override
    public void addSyrup(String flavor) {
        if (current != null && flavor != null && !flavor.isBlank()) {
            current = new SyrupDecorator(current, flavor.trim());
        }
    }

    @Override
    public void finalizeBeverage() {
        if (current != null) {
            beverages.add(current);
            current = null;
        }
    }

    public DrinkOrderBuilder addBeverageDirect(Beverage beverage) {
        beverages.add(beverage);
        return this;
    }

    public DrinkOrder build() {
        return new DrinkOrder(beverages);
    }

    @Override
    public DrinkOrder buildOrder() {
        return build();
    }

    @Override
    public OrderMemento createMemento() {
        return new OrderMemento(beverages);
    }

    @Override
    public void restore(OrderMemento memento) {
        beverages.clear();
        beverages.addAll(memento.getState());
    }

    @Override
    public List<Beverage> getCurrentBeverages() {
        return beverages;
    }
}
