package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.mcr.McrExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.mcr.MCRService;
import at.o2xfs.xfs.service.util.ExceptionUtil;

/**
 * 吞包开始,准备存储空盘，运送到客户区后，打开阀门。
 */
public class RetainSlotStartCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final MCRService mcrService;

    public RetainSlotStartCommand(MCRService mcrService) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }
        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.RETAIN_SLOT_START);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
