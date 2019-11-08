package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.pin.ImportRSAPublicKeyOutput3;

public class PINImportRSAPublicKeyCompleteEvent implements CompleteEvent<ImportRSAPublicKeyOutput3> {

    private final ImportRSAPublicKeyOutput3 importRSAPublicKeyOutput3;

    public PINImportRSAPublicKeyCompleteEvent(ImportRSAPublicKeyOutput3 importRSAPublicKeyOutput3) {
        this.importRSAPublicKeyOutput3 = importRSAPublicKeyOutput3;
    }

    @Override
    public ImportRSAPublicKeyOutput3 get() {
        return importRSAPublicKeyOutput3;
    }
}
