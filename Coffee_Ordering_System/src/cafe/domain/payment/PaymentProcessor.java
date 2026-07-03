package cafe.domain.payment;

// Product for Factory Method.
public interface PaymentProcessor {
    boolean process(double amount);
    String getName();
}
