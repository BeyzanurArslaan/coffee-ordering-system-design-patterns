package cafe.domain;

public class Configuration {
    // Singleton for tax and flags.
    private static volatile Configuration instance;
    private double taxRate = 0.08;
    private boolean happyHour = false;
    private Configuration() {}

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isHappyHour() {
        return happyHour;
    }

    public void setHappyHour(boolean happyHour) {
        this.happyHour = happyHour;
    }
}
