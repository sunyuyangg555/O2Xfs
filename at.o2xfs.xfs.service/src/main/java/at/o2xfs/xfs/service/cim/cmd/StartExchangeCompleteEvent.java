package at.o2xfs.xfs.service.cim.cmd;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;

import java.util.Objects;
import java.util.Optional;

public class StartExchangeCompleteEvent implements CompleteEvent {

    private Optional<CashInfo3> cashInfo;

    private StartExchangeCompleteEvent(Optional<CashInfo3> cashInfo) {
        Objects.requireNonNull(cashInfo);
        this.cashInfo = cashInfo;
    }

    public Optional<CashInfo3> getCashInfo() {
        return cashInfo;
    }

    public static final StartExchangeCompleteEvent build(Optional<CashInfo3> cashInfo) {
        return new StartExchangeCompleteEvent(cashInfo);
    }
}
