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
import at.o2xfs.xfs.v3_10.mcr.RetainSlot;

/**
 * 存储外币包结束，并写入存包信息到硬件存储。
 */
public class RetainSlotEndCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final MCRService mcrService;

    private final RetainSlot retainSlot;

    public RetainSlotEndCommand(MCRService mcrService, RetainSlot retainSlot) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }
        this.retainSlot = retainSlot;
        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.RETAIN_SLOT_END, retainSlot);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
