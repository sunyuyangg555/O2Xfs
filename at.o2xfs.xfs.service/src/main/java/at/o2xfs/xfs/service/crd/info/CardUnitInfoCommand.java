package at.o2xfs.xfs.service.crd.info;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.crd.CrdInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.crd.CrdFactory;
import at.o2xfs.xfs.service.crd.CrdService;
import at.o2xfs.xfs.v3_00.crd.CardUnitInfo3;

import java.util.concurrent.Callable;

public class CardUnitInfoCommand implements Callable<CardUnitInfo3> {

    private final CrdService crdService;

    public CardUnitInfoCommand(CrdService crdService) {
        this.crdService = crdService;
    }

    @Override
    public CardUnitInfo3 call() throws Exception {
        CardUnitInfo3 result;
        XfsInfoCommand<CrdInfoCommand> command = new XfsInfoCommand<>(crdService, CrdInfoCommand.UNIT_INFO);
        WFSResult wfsResult = null;
        try {
            wfsResult = command.call();
            result = CrdFactory.create(crdService.getXfsVersion(), wfsResult.getResults(), CardUnitInfo3.class);
            return result;
        } finally {
            if (wfsResult != null) {
                XfsServiceManager.getInstance().free(wfsResult);
            }
        }
    }
}
