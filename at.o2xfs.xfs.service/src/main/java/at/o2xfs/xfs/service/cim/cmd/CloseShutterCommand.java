package at.o2xfs.xfs.service.cim.cmd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;

public class CloseShutterCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CimService cimService;
    private final Pointer position;

    public CloseShutterCommand(CimService cimService, Pointer position) {
        this.cimService = cimService;
        this.position = position;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(cimService, CimExecuteCommand.CLOSE_SHUTTER, position);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
