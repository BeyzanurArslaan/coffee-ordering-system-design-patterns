package cafe.domain.preparation;

// Concrete implementor for pour-over.
public class PourOverStation implements PreparationImplementor {
    @Override
    public void heatWater() {
        System.out.println("Kettle heating water for pour-over");
    }

    @Override
    public void brew(String base) {
        System.out.println("Pour-over brewing: " + base);
    }

    @Override
    public void blend(String ingredients) {
        // not used
    }

    @Override
    public void finish(String drinkName) {
        System.out.println("Serving " + drinkName + " in glass");
    }
}
