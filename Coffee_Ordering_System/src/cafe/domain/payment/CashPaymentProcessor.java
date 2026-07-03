package cafe.domain.payment;

// Concrete product for cash.
public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Accepting cash: " + amount);
        return true;
    }

    @Override
    public String getName() {
        return "Cash";
    }
}
