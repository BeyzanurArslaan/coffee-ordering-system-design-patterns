package cafe.domain.order;

import cafe.domain.Configuration;
import cafe.domain.payment.CardPaymentFactory;
import cafe.domain.payment.CashPaymentFactory;
import cafe.domain.payment.PaymentProcessor;
import cafe.domain.payment.PaymentProcessorFactory;
import cafe.domain.payment.PaymentType;
import cafe.domain.payment.QrPaymentFactory;
import cafe.domain.pricing.PricingStrategy;
import cafe.domain.validation.OrderContext;
import cafe.domain.validation.OrderHandler;

// Service that uses Strategy, Factory, and Chain.
public class OrderService {
    private PricingStrategy pricingStrategy;
    private OrderHandler validationChain;

    public OrderService(PricingStrategy pricingStrategy, OrderHandler validationChain) {
        this.pricingStrategy = pricingStrategy;
        this.validationChain = validationChain;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public boolean submitOrder(DrinkOrder order, PaymentType paymentType, int pendingOrders) {
        OrderContext context = new OrderContext(order, paymentType, pendingOrders);
        if (!validationChain.handle(context)) {
            return false;
        }
        double subtotal = pricingStrategy.applyPrice(order);
        double taxed = subtotal + (subtotal * Configuration.getInstance().getTaxRate());
        PaymentProcessor processor = createPaymentProcessor(paymentType).createProcessor();
        boolean paid = processor.process(taxed);
        if (paid) {
            System.out.println("Order total (with tax) processed by " + processor.getName() + ": " + taxed);
        }
        return paid;
    }

    private PaymentProcessorFactory createPaymentProcessor(PaymentType type) {
        switch (type) {
            case CASH:
                return new CashPaymentFactory();
            case QR:
                return new QrPaymentFactory();
            case CARD:
            default:
                return new CardPaymentFactory();
        }
    }
}
