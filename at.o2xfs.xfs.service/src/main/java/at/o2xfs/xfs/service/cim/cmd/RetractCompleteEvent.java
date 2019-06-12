package at.o2xfs.xfs.service.cim.cmd;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;

import java.util.Objects;
import java.util.Optional;

public class RetractCompleteEvent implements CompleteEvent {

    private Optional<CashInfo3> cashInfo;

    private RetractCompleteEvent(Optional<CashInfo3> cashInfo) {
        Objects.requireNonNull(cashInfo);
        this.cashInfo = cashInfo;
    }

    public Optional<CashInfo3> getCashInfo() {
        return cashInfo;
    }

    public static final RetractCompleteEvent build(Optional<CashInfo3> cashInfo) {
        return new RetractCompleteEvent(cashInfo);
    }
}
