package cafe.domain.report;

import cafe.domain.menu.MenuCategory;
import cafe.domain.menu.MenuItem;

// Visitor for menu nodes.
public interface MenuVisitor {
    void visitCategory(MenuCategory category);
    void visitItem(MenuItem item);
}
