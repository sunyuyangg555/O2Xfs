package at.o2xfs.xfs.service.bcr.info;

import at.o2xfs.xfs.bcr.BcrInfoCommand;
import at.o2xfs.xfs.service.ReflectiveInfoCommand;
import at.o2xfs.xfs.service.bcr.BCRService;
import at.o2xfs.xfs.v3_10.bcr.Status310;

public class BcrStatusCommand extends ReflectiveInfoCommand<BCRService, BcrInfoCommand, Status310> {
    public BcrStatusCommand(BCRService service) {
        super(service, BcrInfoCommand.STATUS, Status310.class);
    }
}
