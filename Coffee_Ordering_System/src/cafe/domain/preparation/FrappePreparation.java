package cafe.domain.preparation;

// Concrete Template for frappe.
public class FrappePreparation extends DrinkPreparationTemplate {
    public FrappePreparation(PreparationImplementor implementor) {
        super("Frappe", implementor);
    }

    @Override
    protected void prepareBase() {
        implementor.blend("coffee, ice");
    }

    @Override
    protected void addIngredients() {
        System.out.println("Adding whipped cream topping");
    }
}
