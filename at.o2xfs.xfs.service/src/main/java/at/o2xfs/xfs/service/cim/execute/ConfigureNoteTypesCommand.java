package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.service.cim.CimService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.v3_00.cim.NoteIDs;

public class ConfigureNoteTypesCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final CimService cimService;

    private NoteIDs noteIds;

    public ConfigureNoteTypesCommand(int[] ids, CimService cimService) {
        noteIds = new NoteIDs(ids);
        this.cimService = cimService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(cimService, CimExecuteCommand.CONFIGURE_NOTETYPES, noteIds);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
