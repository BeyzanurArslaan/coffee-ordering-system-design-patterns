package cafe.domain.menu;

// Builder pattern: assembles sample menu tree for demos/tests.
public class MenuBuilder {
    public static MenuCategory sampleMenu() {
        MenuCategory root = new MenuCategory("Cafe Menu");
        MenuCategory espresso = new MenuCategory("Espresso Drinks");
        espresso.add(new MenuItem("Espresso", 3.0, false));
        espresso.add(new MenuItem("Latte", 4.0, true));
        espresso.add(new MenuItem("Mocha", 4.5, true));

        MenuCategory cold = new MenuCategory("Cold Drinks");
        cold.add(new MenuItem("Iced Latte", 4.2, true));
        cold.add(new MenuItem("Frappe", 4.8, true));

        root.add(espresso);
        root.add(cold);
        return root;
    }
}
