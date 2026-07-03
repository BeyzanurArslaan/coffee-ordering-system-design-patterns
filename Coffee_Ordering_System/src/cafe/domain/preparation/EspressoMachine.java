package cafe.domain.preparation;

// Concrete implementor for espresso gear.
public class EspressoMachine implements PreparationImplementor {
    @Override
    public void heatWater() {
        System.out.println("Espresso machine heating water");
    }

    @Override
    public void brew(String base) {
        System.out.println("Brewing espresso base: " + base);
    }

    @Override
    public void blend(String ingredients) {
        // not used for espresso drinks
    }

    @Override
    public void finish(String drinkName) {
        System.out.println("Serving " + drinkName + " in ceramic cup");
    }
}
