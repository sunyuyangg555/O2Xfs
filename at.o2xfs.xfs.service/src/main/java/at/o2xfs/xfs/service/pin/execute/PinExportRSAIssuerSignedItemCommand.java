package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.v3_00.pin.ExportRSAIssuerSignedItem3;
import at.o2xfs.xfs.v3_00.pin.ExportRSAIssuerSignedItemOutput3;

public class PinExportRSAIssuerSignedItemCommand extends AbstractAsyncXfsCommand<CommandListener<PinExportRSAIssuerSignedItemCompleteEvent>, PinExportRSAIssuerSignedItemCompleteEvent> {

    private final PINService pinService;
    private final ExportRSAIssuerSignedItem3 exportRSAIssuerSignedItem3;

    public PinExportRSAIssuerSignedItemCommand(PINService pinService, ExportRSAIssuerSignedItem3 exportRSAIssuerSignedItem3) {
        this.pinService = pinService;
        this.exportRSAIssuerSignedItem3 = exportRSAIssuerSignedItem3;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand(pinService, PINExecuteCommand.EXPORT_RSA_ISSUER_SIGNED_ITEM, exportRSAIssuerSignedItem3);
    }

    @Override
    protected PinExportRSAIssuerSignedItemCompleteEvent createCompleteEvent(Pointer results) {
        return new PinExportRSAIssuerSignedItemCompleteEvent(new ExportRSAIssuerSignedItemOutput3(results));
    }
}
