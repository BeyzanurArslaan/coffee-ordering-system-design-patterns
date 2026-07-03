package cafe.domain.menu;

import cafe.domain.report.MenuVisitor;

import java.util.ArrayList;
import java.util.List;

// Composite pattern base component; participates in Visitor double-dispatch.
public abstract class MenuComponent {
    protected String name;

    public MenuComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public List<MenuComponent> getChildren() {
        return new ArrayList<>();
    }

    // Visitor pattern dispatch point.
    public abstract void accept(MenuVisitor visitor);
}
