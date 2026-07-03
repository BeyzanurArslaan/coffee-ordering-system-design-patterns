package cafe.domain.payment;

// Factory Method base.
public abstract class PaymentProcessorFactory {
    public abstract PaymentProcessor createProcessor();
}
