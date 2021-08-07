package in.shabhushan.lld.expense.strategy;

import in.shabhushan.lld.expense.strategy.impl.MultiPayPaymentStrategy;
import in.shabhushan.lld.expense.strategy.impl.SelfPayPaymentStrategy;

import javax.ws.rs.NotSupportedException;

public class PaymentStrategyFactory {
    private PaymentStrategyFactory() {}

    public static PaymentStrategy forName(String name) {
        if (name.equals("SelfPay")) {
            return new SelfPayPaymentStrategy();
        } else if (name.equals("MultiPay")) {
            return new MultiPayPaymentStrategy();
        } else {
            throw new NotSupportedException("Payment Strategy " + name + " does not exist");
        }
    }
}
