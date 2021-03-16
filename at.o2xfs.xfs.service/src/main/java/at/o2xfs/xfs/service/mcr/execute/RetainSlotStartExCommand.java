package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.mcr.McrExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.mcr.MCRService;
import at.o2xfs.xfs.service.mcr.McrFactory;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartIn310;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartOut310;

/**
 * 吞包开始,准备存储空盘，运送到客户区后，打开阀门。
 */
public class RetainSlotStartExCommand extends AbstractAsyncXfsCommand<CommandListener<RetainSlotStartExCompleteEvent>, RetainSlotStartExCompleteEvent> {

    private final MCRService mcrService;

    private final RetainSlotStartIn310 retainSlotStartIn310;

    public RetainSlotStartExCommand(MCRService mcrService, RetainSlotStartIn310 retainSlotStartIn310) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }

        this.retainSlotStartIn310 = retainSlotStartIn310;

        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.RETAIN_SLOT_START_EX, retainSlotStartIn310);
    }

    @Override
    protected RetainSlotStartExCompleteEvent createCompleteEvent(Pointer results) {
        return new RetainSlotStartExCompleteEvent(
                McrFactory.INSTANCE.create(
                        mcrService.getXfsVersion(),
                        results,
                        RetainSlotStartOut310.class
                ));
    }
}
