package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.idc.IdcExecuteCommand;
import at.o2xfs.xfs.idc.ResetIn;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.idc.IDCService;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.win32.XfsWord;

public class ResetCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final IDCService idcService;
    private XfsWord<ResetIn> xfsWord;

    public ResetCommand(IDCService idcService, ResetIn resetIn) {
        if (idcService == null) {
            ExceptionUtil.nullArgument("idcService");
        }
        this.idcService = idcService;
        xfsWord = new XfsWord<>(ResetIn.class);
        xfsWord.allocate();
        xfsWord.set(resetIn);
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(idcService, IdcExecuteCommand.RESET, new Pointer(xfsWord));
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
