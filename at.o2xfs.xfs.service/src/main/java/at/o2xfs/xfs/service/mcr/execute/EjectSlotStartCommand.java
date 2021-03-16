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
import at.o2xfs.xfs.v3_10.mcr.EjectSlot310;
import at.o2xfs.xfs.v3_10.mcr.EjectSlotOut310;

import java.util.Optional;

/**
 * 将指定外币包运送传输通道。如传入的二维码信息与取到的封包二维码信息不一致，返回固定错
 * 误码(WFS_ERR_MCR_CODENOTMATCH)，并且返回最新扫描的封包二维码信息；如成功或者
 * 其他的错误码，只需返回执行返回值即可。
 */
public class EjectSlotStartCommand extends AbstractAsyncXfsCommand<CommandListener<EjectSlotStartCompleteEvent>, EjectSlotStartCompleteEvent> {

    private final MCRService mcrService;

    private final EjectSlot310 ejectSlot310;

    public EjectSlotStartCommand(MCRService mcrService, EjectSlot310 ejectSlot310) {
        if (mcrService == null) {
            ExceptionUtil.nullArgument("mcrService");
        }
        this.ejectSlot310 = ejectSlot310;
        this.mcrService = mcrService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(mcrService, McrExecuteCommand.EJECT_SLOT_START, ejectSlot310);
    }

    @Override
    protected EjectSlotStartCompleteEvent createCompleteEvent(Pointer results) {
        Optional<EjectSlotOut310> ejectSlotOut = Optional.empty();
        if (!Pointer.NULL.equals(results)) {
            ejectSlotOut = Optional.of(McrFactory.INSTANCE.create(mcrService.getXfsVersion(), results, EjectSlotOut310.class));
        }
        return EjectSlotStartCompleteEvent.build(ejectSlotOut);
    }
}
