package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.v3_00.pin.ImportRSAPublicKey3;
import at.o2xfs.xfs.v3_00.pin.ImportRSAPublicKeyOutput3;


public class PINImportRSAPublicKeyCommand extends AbstractAsyncXfsCommand<CommandListener<PINImportRSAPublicKeyCompleteEvent>, PINImportRSAPublicKeyCompleteEvent> {

    private final ImportRSAPublicKey3 importRSAPublicKey3;
    private final PINService pinService;

    public PINImportRSAPublicKeyCommand(ImportRSAPublicKey3 importRSAPublicKey3, PINService pinService) {
        this.importRSAPublicKey3 = importRSAPublicKey3;
        this.pinService = pinService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(pinService, PINExecuteCommand.IMPORT_RSA_PUBLIC_KEY, importRSAPublicKey3);
    }

    @Override
    protected PINImportRSAPublicKeyCompleteEvent createCompleteEvent(Pointer results) {
        return new PINImportRSAPublicKeyCompleteEvent(new ImportRSAPublicKeyOutput3(results));
    }
}
