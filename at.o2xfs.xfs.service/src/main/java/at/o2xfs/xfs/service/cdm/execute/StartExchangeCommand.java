package at.o2xfs.xfs.service.cdm.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.cdm.CdmExecuteCommand;
import at.o2xfs.xfs.cdm.CdmMessage;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cdm.CdmService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.v3_00.cdm.CashUnit3;
import at.o2xfs.xfs.v3_00.cdm.CashUnitError3;
import at.o2xfs.xfs.v3_00.cdm.CashUnitInfo3;
import at.o2xfs.xfs.v3_00.cdm.StartEx3;


public final class StartExchangeCommand extends AbstractAsyncXfsCommand<StartExchangeListener, StartExchangeCompleteEvent> {

    private final CdmService cdmService;
    private final StartEx3 startEx;

    public StartExchangeCommand(CdmService cdmService, StartEx3 startEx) {
        this.cdmService = cdmService;
        this.startEx = startEx;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(cdmService, CdmExecuteCommand.START_EXCHANGE, startEx);
    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        try {
            CdmMessage message = wfsResult.getEventID(CdmMessage.class);
            switch (message) {
                case EXEE_CASHUNITERROR:
                    fireCashUnitError(new CashUnitError3(new CashUnitError3(wfsResult.getResults())));
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
        return StartExchangeCompleteEvent.build(new CashUnitInfo3(new CashUnitInfo3(results)));
    }
}
