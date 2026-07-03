package cafe.domain.menu;

import cafe.domain.report.MenuVisitor;

// Composite pattern leaf; participates in Visitor.
public class MenuItem extends MenuComponent {
    private final double price;
    private final boolean hasAllergens;

    public MenuItem(String name, double price, boolean hasAllergens) {
        super(name);
        this.price = price;
        this.hasAllergens = hasAllergens;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean hasAllergens() {
        return hasAllergens;
    }

    @Override
    public void accept(MenuVisitor visitor) {
        visitor.visitItem(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}
