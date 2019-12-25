package at.o2xfs.xfs.service.bcr.info;

import at.o2xfs.xfs.bcr.BcrInfoCommand;
import at.o2xfs.xfs.service.ReflectiveInfoCommand;
import at.o2xfs.xfs.service.bcr.BCRService;
import at.o2xfs.xfs.v3_10.bcr.Capabilities310;

public class BcrCapabilitiesCommand extends ReflectiveInfoCommand<BCRService, BcrInfoCommand, Capabilities310> {
    public BcrCapabilitiesCommand(BCRService service) {
        super(service, BcrInfoCommand.CAPABILITIES, Capabilities310.class);
    }
}
