package at.o2xfs.xfs.service.cim.execute;


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
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import at.o2xfs.xfs.v3_00.cim.CashUnitError3;
import at.o2xfs.xfs.v3_00.cim.StartEx3;

import java.util.Optional;


public final class StartExchangeCommand extends AbstractAsyncXfsCommand<StartExchangeListener, StartExchangeCompleteEvent> {

    private final CimService cimService;
    private final StartEx3 startEx;

    public StartExchangeCommand(CimService cimService, StartEx3 startEx) {
        this.cimService = cimService;
        this.startEx =startEx;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(cimService, CimExecuteCommand.START_EXCHANGE, new Pointer(startEx));
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

    private void fireCashUnitError(CashUnitError3 cashUnitError) {
        for (StartExchangeListener each : listeners) {
            each.onCashUnitError(cashUnitError);
        }
    }


    @Override
    protected StartExchangeCompleteEvent createCompleteEvent(Pointer results) {
        return StartExchangeCompleteEvent.build(CimFactory.create(cimService.getSrvcVersion().getVersion(), results, CashInfo3.class));
    }
}
