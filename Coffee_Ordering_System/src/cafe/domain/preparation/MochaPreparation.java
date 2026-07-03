package cafe.domain.preparation;

// Concrete Template for mocha.
public class MochaPreparation extends DrinkPreparationTemplate {
    public MochaPreparation(PreparationImplementor implementor) {
        super("Mocha", implementor);
    }

    @Override
    protected void prepareBase() {
        implementor.brew("espresso shot");
    }

    @Override
    protected void addIngredients() {
        System.out.println("Adding chocolate syrup and steamed milk");
    }
}
