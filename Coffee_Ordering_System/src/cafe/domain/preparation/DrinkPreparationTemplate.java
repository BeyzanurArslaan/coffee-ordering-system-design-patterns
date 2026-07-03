package cafe.domain.preparation;

// Template Method plus Bridge implementor.
public abstract class DrinkPreparationTemplate {
    protected final PreparationImplementor implementor;
    protected final String drinkName;

    protected DrinkPreparationTemplate(String drinkName, PreparationImplementor implementor) {
        this.drinkName = drinkName;
        this.implementor = implementor;
    }

    public final void prepare() {
        boilWater();
        prepareBase();
        addIngredients();
        finalizeDrink();
    }

    protected void boilWater() {
        implementor.heatWater();
    }

    protected abstract void prepareBase();

    protected abstract void addIngredients();

    protected void finalizeDrink() {
        implementor.finish(drinkName);
    }
}
