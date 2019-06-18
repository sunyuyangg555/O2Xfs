package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;

import java.util.Objects;
import java.util.Optional;

public class StartExchangeCompleteEvent implements CompleteEvent<Optional<CashInfo3>> {

    private Optional<CashInfo3> cashInfo;

    private StartExchangeCompleteEvent(Optional<CashInfo3> cashInfo) {
        Objects.requireNonNull(cashInfo);
        this.cashInfo = cashInfo;
    }


    public static final StartExchangeCompleteEvent build(Optional<CashInfo3> cashInfo) {
        return new StartExchangeCompleteEvent(cashInfo);
    }

    @Override
    public Optional<CashInfo3> get() {
        return cashInfo;
    }
}
