package at.o2xfs.xfs.service.ptr.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.ptr.MediaControl;
import at.o2xfs.xfs.ptr.PtrExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.ptr.PTRService;
import at.o2xfs.xfs.win32.XfsDWord;

public class ControlMediaCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final PTRService ptrService;
    private final XfsDWord<MediaControl> mediaControl = new XfsDWord<>(MediaControl.class);

    public ControlMediaCommand(PTRService ptrService, MediaControl mediaControl) {
        this.ptrService = ptrService;
        this.mediaControl.allocate();
        this.mediaControl.set(mediaControl);
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(ptrService, PtrExecuteCommand.CONTROL_MEDIA, mediaControl);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }

}
