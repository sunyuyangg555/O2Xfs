package at.o2xfs.xfs.service.crd.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.crd.CrdExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.crd.CRDService;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_00.crd.RetainCard3;

public class RetainCardCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CRDService crdService;
    private final RetainCard3 retainCard3;

    public RetainCardCommand(CRDService crdService, RetainCard3 retainCard3) {
        if (crdService == null) {
            ExceptionUtil.nullArgument("crdService");
        }
        this.crdService = crdService;
        this.retainCard3 = retainCard3;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(crdService, CrdExecuteCommand.RETAIN_CARD, retainCard3);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
