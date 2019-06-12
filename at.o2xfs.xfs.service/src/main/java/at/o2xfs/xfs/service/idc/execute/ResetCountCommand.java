package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.idc.IdcExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.util.ExceptionUtil;

public class ResetCountCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final IDCService idcService;

    public ResetCountCommand(IDCService idcService) {
        if (idcService == null) {
            ExceptionUtil.nullArgument("idcService");
        }
        this.idcService = idcService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(idcService, IdcExecuteCommand.RESET_COUNT);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
