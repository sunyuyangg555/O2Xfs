package at.o2xfs.xfs.service.crd.info;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.crd.CrdInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.crd.CrdFactory;
import at.o2xfs.xfs.service.crd.CRDService;
import at.o2xfs.xfs.v3_00.crd.CrdStatus3;

import java.util.concurrent.Callable;

public class StatusCommand implements Callable<CrdStatus3> {

    private final CRDService crdService;

    public StatusCommand(CRDService crdService) {
        this.crdService = crdService;
    }

    @Override
    public CrdStatus3 call() throws Exception {

        CrdStatus3 result;
        XfsInfoCommand<CrdInfoCommand> command = new XfsInfoCommand<>(crdService, CrdInfoCommand.STATUS);
        WFSResult wfsResult = null;
        try {
            wfsResult = command.call();
            result = CrdFactory.create(crdService.getXfsVersion(), wfsResult.getResults(), CrdStatus3.class);
            return result;
        } finally {
            if (wfsResult != null) {
                XfsServiceManager.getInstance().free(wfsResult);
            }
        }
    }
}
