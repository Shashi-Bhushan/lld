package in.shabhushan.lld.expense.strategy;

import in.shabhushan.lld.expense.strategy.impl.EqualSplitPercentageStrategy;

import javax.ws.rs.NotSupportedException;

public class SplitStrategyFactory {
    private SplitStrategyFactory() {}

    public static SplitStrategy forName(String name) {
        if (name.equals("EqualPayment")) {
            return new EqualSplitPercentageStrategy();
        } else {
            throw new NotSupportedException("Split Strategy " + name + " does not exist");
        }
    }
}
