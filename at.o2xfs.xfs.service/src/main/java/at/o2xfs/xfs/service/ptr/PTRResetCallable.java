package at.o2xfs.xfs.service.ptr;


import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.ptr.PtrExecuteCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.v3_00.ptr.Reset3;

import java.util.concurrent.Callable;

public class PTRResetCallable implements Callable<WFSResult> {

    private final PTRService ptrService;
    private final Reset3 reset3;

    public PTRResetCallable(PTRService ptrService, Reset3 reset3) {
        this.ptrService = ptrService;
        this.reset3 = reset3;
    }

    @Override
    public WFSResult call() throws Exception {
        return new XfsExecuteCommand<>(ptrService, PtrExecuteCommand.RESET, reset3).call();
    }
}
