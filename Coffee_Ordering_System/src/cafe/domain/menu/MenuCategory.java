package cafe.domain.menu;

import cafe.domain.report.MenuVisitor;

import java.util.ArrayList;
import java.util.List;

// Composite pattern composite node holding children.
public class MenuCategory extends MenuComponent {
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuCategory(String name) {
        super(name);
    }

    @Override
    public void add(MenuComponent component) {
        children.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        children.remove(component);
    }

    @Override
    public List<MenuComponent> getChildren() {
        return children;
    }

    // Visitor pattern traversal.
    @Override
    public void accept(MenuVisitor visitor) {
        visitor.visitCategory(this);
        for (MenuComponent child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
