package cafe.domain.payment;

// Concrete product for card.
public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Processing card payment: " + amount);
        return true;
    }

    @Override
    public String getName() {
        return "Card";
    }
}
