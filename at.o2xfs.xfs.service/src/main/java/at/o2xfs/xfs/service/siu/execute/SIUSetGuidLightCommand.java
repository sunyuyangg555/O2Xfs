package at.o2xfs.xfs.service.siu.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.siu.SIUService;
import at.o2xfs.xfs.service.siu.event.PortErrorListener;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.siu.SIUExecuteCommand;
import at.o2xfs.xfs.siu.SIUMessage;
import at.o2xfs.xfs.siu.SIUPortError;
import at.o2xfs.xfs.v3_10.siu.SetGuidlight310;

public class SIUSetGuidLightCommand extends AbstractAsyncXfsCommand<SetGuidLightListener, SuccessEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SIUSetGuidLightCommand.class);
    private final SIUService siuService;
    private final SetGuidlight310 setGuidlight310;

    public SIUSetGuidLightCommand(final SetGuidlight310 setGuidlight310, final SIUService siuService) {
        if (siuService == null) {
            ExceptionUtil.nullArgument("siuService");
        }
        this.siuService = siuService;
        this.setGuidlight310 = setGuidlight310;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(siuService, SIUExecuteCommand.SET_GUIDLIGHT, setGuidlight310);
    }

    @Override
    public void fireIntermediateEvent(final WFSResult wfsResult) {
        try {
            final String method = "fireIntermediateEvent(WFSResult)";
            final SIUMessage message = wfsResult.getEventID(SIUMessage.class);
            switch (message) {
                case PORT_ERROR:
                    final SIUPortError portError = new SIUPortError(
                            wfsResult.getResults());
                    if (LOG.isInfoEnabled()) {
                        LOG.info(method, portError);
                    }
                    notifyPortError(portError);
                    break;
                default:
                    if (LOG.isWarnEnabled()) {
                        LOG.warn(method, "Unknown intermediate event: "
                                + wfsResult);
                    }
            }
        } finally {
            if (wfsResult != null) {
                XfsServiceManager.getInstance().free(wfsResult);
            }
        }
    }

    private void notifyPortError(final SIUPortError portError) {
        for (final PortErrorListener l : listeners) {
            l.onPortError(new SIUPortError(portError));
        }
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
