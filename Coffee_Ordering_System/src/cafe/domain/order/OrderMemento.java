package cafe.domain.order;

import java.util.ArrayList;
import java.util.List;

// Memento snapshot for undo/redo.
public class OrderMemento {
    private final List<Beverage> beveragesSnapshot;

    public OrderMemento(List<Beverage> beveragesSnapshot) {
        this.beveragesSnapshot = new ArrayList<>(beveragesSnapshot);
    }

    public List<Beverage> getState() {
        return new ArrayList<>(beveragesSnapshot);
    }
}
