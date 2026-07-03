package cafe.domain.payment;

// Concrete Factory for cash.
public class CashPaymentFactory extends PaymentProcessorFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new CashPaymentProcessor();
    }
}
