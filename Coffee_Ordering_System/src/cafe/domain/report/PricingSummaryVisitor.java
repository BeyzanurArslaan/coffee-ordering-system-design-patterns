package cafe.domain.report;

import cafe.domain.menu.MenuCategory;
import cafe.domain.menu.MenuItem;

// Visitor that sums menu prices.
public class PricingSummaryVisitor implements MenuVisitor {
    private double total;
    private int itemCount;

    @Override
    public void visitCategory(MenuCategory category) {
        // categories do not affect totals directly
    }

    @Override
    public void visitItem(MenuItem item) {
        total += item.getPrice();
        itemCount++;
    }

    public double getTotal() {
        return total;
    }

    public int getItemCount() {
        return itemCount;
    }
}
