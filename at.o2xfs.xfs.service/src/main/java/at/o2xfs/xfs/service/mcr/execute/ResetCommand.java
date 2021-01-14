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
 * 清除以前的二维码与槽号的对应关系，开启全盘扫描，重新建立二维码与槽号、存包位置之间的
 * 映射关系,并且硬件存储本次清机完成时间。
 */
public class ResetCommand extends AbstractAsyncXfsCommand<CommandListener<SuccessEvent>, SuccessEvent> {

    private final MCRService mcrService;

    public ResetCommand(MCRService mcrService) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }
        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.RESET);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
