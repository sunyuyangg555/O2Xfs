package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;

import java.util.Objects;
import java.util.Optional;

public class StartExchangeCompleteEvent implements CompleteEvent<CashInfo3> {

    private CashInfo3 cashInfo;

    private StartExchangeCompleteEvent(CashInfo3 cashInfo) {
        Objects.requireNonNull(cashInfo);
        this.cashInfo = cashInfo;
    }


    public static final StartExchangeCompleteEvent build(CashInfo3 cashInfo) {
        return new StartExchangeCompleteEvent(cashInfo);
    }

    @Override
    public CashInfo3 get() {
        return cashInfo;
    }
}
