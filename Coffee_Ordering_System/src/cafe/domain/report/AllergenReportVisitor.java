package cafe.domain.report;

import cafe.domain.menu.MenuCategory;
import cafe.domain.menu.MenuItem;

// Visitor that lists allergen items.
public class AllergenReportVisitor implements MenuVisitor {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public void visitCategory(MenuCategory category) {
        builder.append("Category: ").append(category.getName()).append("\n");
    }

    @Override
    public void visitItem(MenuItem item) {
        if (item.hasAllergens()) {
            builder.append(" - ").append(item.getName()).append(" contains allergens\n");
        }
    }

    public String getReport() {
        return builder.toString();
    }
}
