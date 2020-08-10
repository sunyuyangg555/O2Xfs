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

public class EjectCardCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CRDService crdService;

    public EjectCardCommand(CRDService crdService) {
        if (crdService == null) {
            ExceptionUtil.nullArgument("crdService");
        }
        this.crdService = crdService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(crdService, CrdExecuteCommand.EJECT_CARD);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
