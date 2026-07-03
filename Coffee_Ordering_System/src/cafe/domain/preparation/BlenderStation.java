package cafe.domain.preparation;

// Concrete implementor for blender.
public class BlenderStation implements PreparationImplementor {
    @Override
    public void heatWater() {
        // not needed
    }

    @Override
    public void brew(String base) {
        // not needed for blended drinks
    }

    @Override
    public void blend(String ingredients) {
        System.out.println("Blending: " + ingredients);
    }

    @Override
    public void finish(String drinkName) {
        System.out.println("Serving chilled " + drinkName);
    }
}
