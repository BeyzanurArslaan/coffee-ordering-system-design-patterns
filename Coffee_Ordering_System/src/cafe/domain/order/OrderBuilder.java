package cafe.domain.order;

import java.util.List;

// Builder steps for a drink; used by the director and memento.
public interface OrderBuilder {
    void startBeverage(String name, double price);
    void addMilk();
    void addExtraShot();
    void addSyrup(String flavor);
    void finalizeBeverage();
    DrinkOrder buildOrder();
    OrderMemento createMemento();
    void restore(OrderMemento memento);
    List<Beverage> getCurrentBeverages();
}
