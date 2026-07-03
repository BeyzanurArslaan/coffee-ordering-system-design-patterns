package cafe.domain.preparation;

// Bridge implementor for machines.
public interface PreparationImplementor {
    void heatWater();
    void brew(String base);
    void blend(String ingredients);
    void finish(String drinkName);
}
