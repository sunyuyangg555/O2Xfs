package at.o2xfs.xfs.service.bcr.info;

import at.o2xfs.xfs.bcr.BcrInfoCommand;
import at.o2xfs.xfs.service.ReflectiveInfoCommand;
import at.o2xfs.xfs.service.bcr.BcrService;
import at.o2xfs.xfs.v3_10.bcr.Status310;

public class BcrStatusCommand extends ReflectiveInfoCommand<BcrService, BcrInfoCommand, Status310> {
    public BcrStatusCommand(BcrService service) {
        super(service, BcrInfoCommand.STATUS, Status310.class);
    }
}
