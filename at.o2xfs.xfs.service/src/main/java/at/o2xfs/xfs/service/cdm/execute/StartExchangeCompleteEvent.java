package at.o2xfs.xfs.service.cdm.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cdm.CashUnit3;

import java.util.Objects;

public class StartExchangeCompleteEvent implements CompleteEvent<CashUnit3> {

    private CashUnit3 cashUnit3;

    private StartExchangeCompleteEvent(CashUnit3 cashUnit3) {
        Objects.requireNonNull(cashUnit3);
        this.cashUnit3 = cashUnit3;
    }


    public static final StartExchangeCompleteEvent build(CashUnit3 cashUnit3) {
        return new StartExchangeCompleteEvent(cashUnit3);
    }

    @Override
    public CashUnit3 get() {
        return cashUnit3;
    }
}
