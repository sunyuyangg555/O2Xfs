package at.o2xfs.xfs.service.bcr.info;

import at.o2xfs.xfs.bcr.BcrInfoCommand;
import at.o2xfs.xfs.service.ReflectiveInfoCommand;
import at.o2xfs.xfs.service.bcr.BcrService;
import at.o2xfs.xfs.v3_10.bcr.Capabilities310;

public class BcrCapabilitiesCommand extends ReflectiveInfoCommand<BcrService, BcrInfoCommand, Capabilities310> {
    public BcrCapabilitiesCommand(BcrService service) {
        super(service, BcrInfoCommand.CAPABILITIES, Capabilities310.class);
    }
}
