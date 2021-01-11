package at.o2xfs.xfs.service.ptr.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.ptr.PtrExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.ptr.PTRService;
import at.o2xfs.xfs.service.ptr.PtrFactory;
import at.o2xfs.xfs.v3_00.ptr.ReadForm3;
import at.o2xfs.xfs.v3_00.ptr.ReadFormOut3;

public class ReadFormCommand extends AbstractAsyncXfsCommand<ReadFormListener, ReadFormOutEvent> {

    private final ReadForm3 readForm3;
    private final PTRService ptrService;

    public ReadFormCommand(ReadForm3 readForm3, PTRService ptrService) {
        this.readForm3 = readForm3;
        this.ptrService = ptrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(ptrService, PtrExecuteCommand.READ_FORM, readForm3);
    }

    @Override
    protected ReadFormOutEvent createCompleteEvent(Pointer results) {
        ReadFormOut3 readFormOut3 = PtrFactory.INSTANCE.create(ptrService.getXfsVersion(), results, ReadFormOut3.class);
        return ReadFormOutEvent.build(readFormOut3);
    }
}
