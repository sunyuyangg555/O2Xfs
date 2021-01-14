package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.mcr.McrExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.mcr.MCRService;
import at.o2xfs.xfs.service.mcr.McrFactory;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartIn;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartOut;

/**
 * 吞包开始,准备存储空盘，运送到客户区后，打开阀门。
 */
public class RetainSlotStartExCommand extends AbstractAsyncXfsCommand<CommandListener<RetainSlotStartExCompleteEvent>, RetainSlotStartExCompleteEvent> {

    private final MCRService mcrService;

    private final RetainSlotStartIn retainSlotStartIn;

    public RetainSlotStartExCommand(MCRService mcrService, RetainSlotStartIn retainSlotStartIn) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }

        this.retainSlotStartIn = retainSlotStartIn;

        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.RETAIN_SLOT_START_EX, retainSlotStartIn);
    }

    @Override
    protected RetainSlotStartExCompleteEvent createCompleteEvent(Pointer results) {
        return new RetainSlotStartExCompleteEvent(
                McrFactory.INSTANCE.create(
                        mcrService.getXfsVersion(),
                        results,
                        RetainSlotStartOut.class
                ));
    }
}
