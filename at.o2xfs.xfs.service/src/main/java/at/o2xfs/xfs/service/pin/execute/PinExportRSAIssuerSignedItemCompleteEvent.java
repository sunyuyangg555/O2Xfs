package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.pin.ExportRSAIssuerSignedItemOutput3;

public class PinExportRSAIssuerSignedItemCompleteEvent implements CompleteEvent<ExportRSAIssuerSignedItemOutput3> {

    private final ExportRSAIssuerSignedItemOutput3 exportRSAIssuerSignedItemOutput3;

    public PinExportRSAIssuerSignedItemCompleteEvent(ExportRSAIssuerSignedItemOutput3 exportRSAIssuerSignedItemOutput3) {
        this.exportRSAIssuerSignedItemOutput3 = exportRSAIssuerSignedItemOutput3;
    }

    @Override
    public ExportRSAIssuerSignedItemOutput3 get() {
        return exportRSAIssuerSignedItemOutput3;
    }
}
