package at.o2xfs.xfs.service.cim.event;

import at.o2xfs.xfs.v3_00.cim.CashUnitError3;

public interface CashUnitErrorListener {
    void onCashUnitError(CashUnitError3 cashUnitError);
}
