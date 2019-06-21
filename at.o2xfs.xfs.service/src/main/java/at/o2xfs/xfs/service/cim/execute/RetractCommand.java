package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.cim.CimMessage;
import at.o2xfs.xfs.cim.Reason;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cim.CimFactory;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import at.o2xfs.xfs.v3_00.cim.CashUnitError3;
import at.o2xfs.xfs.v3_00.cim.P6Info3;
import at.o2xfs.xfs.v3_00.cim.Retract3;
import at.o2xfs.xfs.v3_10.cim.ItemInfoSummary310;
import at.o2xfs.xfs.win32.XfsWord;

import java.util.List;
import java.util.Optional;

public class RetractCommand extends AbstractAsyncXfsCommand<RetractListener, RetractCompleteEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(RetractCommand.class);

    private final CimService cimService;
    private Retract3 retract3;

    public RetractCommand(CimService cimService, Retract3 retract3) {
        this.cimService = cimService;
        this.retract3 = retract3;
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<CimExecuteCommand>(cimService, CimExecuteCommand.RETRACT, retract3);
    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("fireIntermediateEvent(WFSResult)", "wfsResult=" + wfsResult);
        }
        try {
            CimMessage message = wfsResult.getEventID(CimMessage.class);
            switch (message) {
                case EXEE_CASHUNITERROR:
                    fireCashUnitError(
                            CimFactory.create(cimService.getXfsVersion(), wfsResult.getResults(), CashUnitError3.class));
                    break;
                case EXEE_NOTEERROR:
                    fireNoteError(new XfsWord<>(Reason.class, wfsResult.getResults()).get());
                    break;
                case EXEE_INPUT_P6:
                    fireInputP6(CimFactory.fromNullTerminatedArray(cimService.getXfsVersion(), wfsResult.getResults(),
                            P6Info3.class));
                    break;
                case EXEE_INFO_AVAILABLE:
                    fireInfoAvailable(CimFactory.fromNullTerminatedArray(cimService.getXfsVersion(), wfsResult.getResults(),
                            ItemInfoSummary310.class));
                    break;
                default:
                    throw new IllegalArgumentException(message.toString());
            }
        } finally {
            XfsServiceManager.getInstance().free(wfsResult);
        }
    }

    private void fireCashUnitError(CashUnitError3 cashUnitError) {
        if (LOG.isInfoEnabled()) {
            LOG.info("fireCashUnitError(CashUnitError3)", "cashUnitError=" + cashUnitError);
        }
        for (RetractListener each : listeners) {
            each.onCashUnitError(cashUnitError);
        }
    }

    private void fireNoteError(Reason reason) {
        if (LOG.isInfoEnabled()) {
            LOG.info("fireNoteError(Reason)", "reason=" + reason);
        }
        for (RetractListener each : listeners) {
            each.onNoteError(reason);
        }
    }

    private void fireInputP6(List<P6Info3> p6Infos) {
        if (LOG.isInfoEnabled()) {
            LOG.info("fireInputP6(Pointer)", "p6Infos=" + p6Infos);
        }
        for (RetractListener each : listeners) {
            each.onInputP6(p6Infos);
        }
    }


    private void fireInfoAvailable(List<ItemInfoSummary310> itemInfoSummaries) {
        if (LOG.isInfoEnabled()) {
            LOG.info("fireInfoAvailable(Pointer)", "itemInfoSummaries=" + itemInfoSummaries);
        }
        for (RetractListener each : listeners) {
            each.onInfoAvailable(itemInfoSummaries);
        }
    }


    @Override
    protected RetractCompleteEvent createCompleteEvent(Pointer results) {
        Optional<CashInfo3> cashInfo3 = Optional.empty();
        if (!Pointer.NULL.equals(results)) {
            cashInfo3 = Optional
                    .of(CimFactory.create(cimService.getSrvcVersion().getVersion(), results, CashInfo3.class));
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("createCompleteEvent(Pointer)", cashInfo3);
        }
        return RetractCompleteEvent.build(cashInfo3);
    }
}
