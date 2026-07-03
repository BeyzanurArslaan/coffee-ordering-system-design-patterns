package cafe.domain.preparation;

// Concrete Template for latte.
public class LattePreparation extends DrinkPreparationTemplate {
    public LattePreparation(PreparationImplementor implementor) {
        super("Latte", implementor);
    }

    @Override
    protected void prepareBase() {
        implementor.brew("espresso shot");
    }

    @Override
    protected void addIngredients() {
        System.out.println("Steaming and adding milk");
    }
}
