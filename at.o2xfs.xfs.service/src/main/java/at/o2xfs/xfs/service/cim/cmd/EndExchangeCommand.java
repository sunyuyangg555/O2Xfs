package at.o2xfs.xfs.service.cim.cmd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.cim.CimMessage;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cim.CimFactory;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import at.o2xfs.xfs.v3_00.cim.CashUnitError3;

public class EndExchangeCommand extends AbstractAsyncXfsCommand<EndExchangeListener, SuccessEvent> {

    private final CimService cimService;
    private final CashInfo3 cashInfo3;

    public EndExchangeCommand(CimService cimService, CashInfo3 cashInfo3) {
        this.cimService = cimService;
        this.cashInfo3 = cashInfo3;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<CimExecuteCommand>(cimService, CimExecuteCommand.END_EXCHANGE, cashInfo3);

    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        try {
            CimMessage message = wfsResult.getEventID(CimMessage.class);
            switch (message) {
                case EXEE_CASHUNITERROR:
                    fireCashUnitError(CimFactory.create(cimService.getXfsVersion(), wfsResult.getResults(), CashUnitError3.class));
                    break;
                default:
                    throw new IllegalArgumentException(message.toString());
            }
        } finally {
            XfsServiceManager.getInstance().free(wfsResult);
        }
    }


    private void fireCashUnitError(CashUnitError3 cashUnitError3) {
        for (EndExchangeListener each : listeners) {
            each.onCashUnitError(cashUnitError3);
        }
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
