package cafe.domain.payment;

// Concrete Factory for QR.
public class QrPaymentFactory extends PaymentProcessorFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new QrPaymentProcessor();
    }
}
