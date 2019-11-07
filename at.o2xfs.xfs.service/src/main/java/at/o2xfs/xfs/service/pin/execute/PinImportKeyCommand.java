package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.v3_00.pin.ImportKey3;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.pin.PINService;

public class PinImportKeyCommand extends AbstractAsyncXfsCommand<CommandListener<PinImportKeyCompleteEvent>, PinImportKeyCompleteEvent> {

    private final PINService pinService;
    private final ImportKey3 importKey;

    public PinImportKeyCommand(ImportKey3 importKey, PINService pinService) {
        this.importKey = importKey;
        this.pinService= pinService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(pinService, PINExecuteCommand.IMPORT_KEY, importKey);
    }

    @Override
    protected PinImportKeyCompleteEvent createCompleteEvent(Pointer results) {
        WfsXData xData = new WfsXData(results);
        return new PinImportKeyCompleteEvent(new WfsXData(xData));
    }

}
