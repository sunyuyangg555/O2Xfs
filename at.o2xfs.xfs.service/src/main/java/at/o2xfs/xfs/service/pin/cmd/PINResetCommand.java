package at.o2xfs.xfs.service.pin.cmd;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.pin.PINInfoCommand;
import at.o2xfs.xfs.pin.WFSPINSTATUS;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.pin.PINService;

import java.util.concurrent.Callable;

public class PINResetCommand implements Callable<WFSResult> {

    private PINService pinService = null;

    public PINResetCommand(PINService pinService) {
        this.pinService = pinService;
    }

    @Override
    public WFSResult call() throws Exception {
        return new XfsExecuteCommand(pinService, PINExecuteCommand.RESET).call();
    }
}
