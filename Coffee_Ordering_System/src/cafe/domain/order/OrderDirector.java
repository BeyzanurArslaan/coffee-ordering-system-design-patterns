package cafe.domain.order;

// Director that runs Builder steps so UI can build drinks safely.
public class OrderDirector {
    private final OrderBuilder builder;

    public OrderDirector(OrderBuilder builder) {
        this.builder = builder;
    }

    // Take options from UI and call builder in the right order.
    public void constructBeverage(String name, double price, boolean addMilk, boolean addShot, String syrupFlavor) {
        builder.startBeverage(name, price);
        if (addMilk) {
            builder.addMilk();
        }
        if (addShot) {
            builder.addExtraShot();
        }
        if (syrupFlavor != null && !syrupFlavor.isBlank()) {
            builder.addSyrup(syrupFlavor);
        }
        builder.finalizeBeverage();
    }
}
