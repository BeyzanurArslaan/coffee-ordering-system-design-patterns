package cafe.domain.validation;

// Chain base handler.
public abstract class OrderHandler {
    private OrderHandler next;

    public OrderHandler linkWith(OrderHandler nextHandler) {
        this.next = nextHandler;
        return nextHandler;
    }

    public boolean handle(OrderContext context) {
        if (!check(context)) {
            return false;
        }
        if (next == null) {
            return true;
        }
        return next.handle(context);
    }

    protected abstract boolean check(OrderContext context);
}
