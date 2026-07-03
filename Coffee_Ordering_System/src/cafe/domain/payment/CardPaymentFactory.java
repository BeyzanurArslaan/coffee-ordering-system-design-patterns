package cafe.domain.payment;

// Concrete Factory for card.
public class CardPaymentFactory extends PaymentProcessorFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new CardPaymentProcessor();
    }
}
