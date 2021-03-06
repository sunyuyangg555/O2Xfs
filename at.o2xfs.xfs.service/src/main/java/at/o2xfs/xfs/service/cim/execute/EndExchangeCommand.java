package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.ZList;
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
import at.o2xfs.xfs.v3_10.cim.CashInfo310;

import java.util.List;
import java.util.Optional;

public class EndExchangeCommand<P extends CashInfo3> extends AbstractAsyncXfsCommand<EndExchangeListener, SuccessEvent> {

    private final CimService cimService;
    private final Optional<P> optionalCashInfo;

    public EndExchangeCommand(CimService cimService, Optional<P> optionalCashInfo) {
        this.cimService = cimService;
        this.optionalCashInfo = optionalCashInfo;
    }

    @Override
    protected XfsCommand createCommand() {
        if (optionalCashInfo.isPresent()) {
            return new XfsExecuteCommand<>(cimService, CimExecuteCommand.END_EXCHANGE, optionalCashInfo.get());
        } else {
            return new XfsExecuteCommand<>(cimService, CimExecuteCommand.END_EXCHANGE);
        }
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
