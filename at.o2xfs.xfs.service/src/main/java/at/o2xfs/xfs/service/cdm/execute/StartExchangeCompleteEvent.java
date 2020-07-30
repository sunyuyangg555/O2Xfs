package at.o2xfs.xfs.service.cdm.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.cdm.CashUnitInfo3;

import java.util.Objects;

public class StartExchangeCompleteEvent implements CompleteEvent<CashUnitInfo3> {

    private CashUnitInfo3 cashUnitInfo3;

    private StartExchangeCompleteEvent(CashUnitInfo3 cashUnitInfo3) {
        Objects.requireNonNull(cashUnitInfo3);
        this.cashUnitInfo3 = cashUnitInfo3;
    }


    public static final StartExchangeCompleteEvent build(CashUnitInfo3 cashUnitInfo3) {
        return new StartExchangeCompleteEvent(cashUnitInfo3);
    }

    @Override
    public CashUnitInfo3 get() {
        return cashUnitInfo3;
    }
}
