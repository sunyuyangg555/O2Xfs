package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.service.util.ExceptionUtil;

public class PinInitializationCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final PINService pinService;

    public PinInitializationCommand(PINService pinService) {
        if (pinService == null) {
            ExceptionUtil.nullArgument("pinService");
        }
        this.pinService = pinService;
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(pinService, PINExecuteCommand.INITIALIZATION);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
