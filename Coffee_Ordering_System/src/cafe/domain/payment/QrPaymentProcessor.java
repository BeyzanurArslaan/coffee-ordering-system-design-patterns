package cafe.domain.payment;

// Concrete product for QR.
public class QrPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Scanning QR for payment: " + amount);
        return true;
    }

    @Override
    public String getName() {
        return "QR";
    }
}
