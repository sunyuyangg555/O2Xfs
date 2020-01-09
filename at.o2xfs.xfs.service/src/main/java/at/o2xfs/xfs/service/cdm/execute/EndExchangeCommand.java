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
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.v3_00.cdm.CashUnit3;
import at.o2xfs.xfs.v3_00.cdm.CashUnitError3;


import java.util.Optional;

public class EndExchangeCommand<P extends CashUnit3> extends AbstractAsyncXfsCommand<EndExchangeListener, SuccessEvent> {

    private final CdmService cdmService;
    private final Optional<P> optionalCashInfo;

    public EndExchangeCommand(CdmService cdmService, Optional<P> optionalCashInfo) {
        this.cdmService = cdmService;
        this.optionalCashInfo = optionalCashInfo;
    }

    @Override
    protected XfsCommand createCommand() {
        if (optionalCashInfo.isPresent()) {
            return new XfsExecuteCommand<>(cdmService, CdmExecuteCommand.END_EXCHANGE, optionalCashInfo.get());
        } else {
            return new XfsExecuteCommand<>(cdmService, CdmExecuteCommand.END_EXCHANGE);
        }
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
