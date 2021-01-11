package at.o2xfs.xfs.service.ptr.info;

import at.o2xfs.win32.LPZZSTR;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.ptr.PtrInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.ptr.PTRService;

import java.util.concurrent.Callable;

public class PTRMediaListCommand implements Callable<String[]> {

    private final PTRService ptrService;

    public PTRMediaListCommand(PTRService ptrService) {
        this.ptrService = ptrService;
    }


    @Override
    public String[] call() throws Exception {
        XfsInfoCommand<PtrInfoCommand>  command = new XfsInfoCommand<>(ptrService, PtrInfoCommand.MEDIA_LIST);
        WFSResult wfsResult = null;
        try {
            wfsResult = command.call();
            LPZZSTR lpzzstr = new LPZZSTR(wfsResult.getResults());
            return lpzzstr.get();
        } finally {
            if (wfsResult != null) {
                XfsServiceManager.getInstance().free(wfsResult);
            }
        }
    }
}
