package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.common.Assert;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.pin.WfsPinCrypt;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.v3_00.pin.Crypt3;

public class PinCryptCommand extends AbstractAsyncXfsCommand<CommandListener<PinCryptCompleteEvent>, PinCryptCompleteEvent> {

    private final PINService pinService;
    private final Crypt3 crypt3;

    public PinCryptCommand( Crypt3 crypt3, PINService pinService) {
        Assert.notNull(pinService);
        Assert.notNull(crypt3);
        this.pinService = pinService;
        this.crypt3 = crypt3;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand(pinService, PINExecuteCommand.CRYPT, crypt3);
    }

    @Override
    protected PinCryptCompleteEvent createCompleteEvent(Pointer results) {
        WfsXData xData = new WfsXData(results);
        return new PinCryptCompleteEvent(new WfsXData(xData));
    }
}
