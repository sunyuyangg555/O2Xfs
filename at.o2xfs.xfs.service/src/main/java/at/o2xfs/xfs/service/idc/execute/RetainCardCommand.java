package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.idc.IdcExecuteCommand;
import at.o2xfs.xfs.idc.IdcMessage;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.idc.IdcFactory;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_00.idc.RetainCard3;

public class RetainCardCommand extends AbstractAsyncXfsCommand<RetainCardListener, RetainCardCompleteEvent> {

    private final IDCService idcService;


    public RetainCardCommand(IDCService idcService) {
        if (idcService == null) {
            ExceptionUtil.nullArgument("idcService");
        }
        this.idcService = idcService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(idcService, IdcExecuteCommand.RETAIN_CARD);
    }

    @Override
    protected RetainCardCompleteEvent createCompleteEvent(Pointer results) {
        return new RetainCardCompleteEvent(IdcFactory.INSTANCE.create(idcService.getXfsVersion(), results, RetainCard3.class));
    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        IdcMessage message = wfsResult.getEventID(IdcMessage.class);
        switch (message) {
            case EXEE_MEDIARETAINED:
                fireMediaRejected();
                break;
            default:
                throw new IllegalArgumentException("IdcMessage: " + message);
        }
    }

    private void fireMediaRejected() {
        listeners.stream().forEach(retainCardListener -> retainCardListener.onMediaRetained());
    }
}
