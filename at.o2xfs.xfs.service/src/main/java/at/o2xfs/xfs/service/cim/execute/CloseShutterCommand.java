package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.cim.Position;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.win32.XfsWord;

public class CloseShutterCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CimService cimService;
    private final XfsWord<Position> positionXfsWord = new XfsWord<>(Position.class);

    public CloseShutterCommand(CimService cimService, Position position) {
        this.cimService = cimService;
        this.positionXfsWord.allocate();
        this.positionXfsWord.set(position);
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(cimService, CimExecuteCommand.CLOSE_SHUTTER, positionXfsWord);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
