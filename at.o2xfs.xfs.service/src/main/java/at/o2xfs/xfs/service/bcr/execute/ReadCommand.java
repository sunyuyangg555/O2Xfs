package at.o2xfs.xfs.service.bcr.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.ZList;
import at.o2xfs.xfs.bcr.BcrExecuteCommand;
import at.o2xfs.xfs.service.bcr.BCRService;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.v3_10.bcr.ReadInput310;
import at.o2xfs.xfs.v3_10.bcr.ReadOutput310;

import java.util.ArrayList;
import java.util.List;

/**
 * This command enables the barcode reader. The barcode reader will scan for barcodes and when it
 * successfully manages to read one or more barcodes the command will complete. The completion
 * event for this command contains the scanned barcode data.
 */
public class ReadCommand extends AbstractAsyncXfsCommand<CommandListener<ReadCompleteEvent>, ReadCompleteEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ReadCommand.class);
    private final BCRService bcrService;
    private final ReadInput310 readInput310;

    public ReadCommand(BCRService bcrService, ReadInput310 readInput310) {
        this.bcrService = bcrService;
        this.readInput310 = readInput310;
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(bcrService, BcrExecuteCommand.READ, readInput310);
    }

    @Override
    protected ReadCompleteEvent createCompleteEvent(Pointer results) {
        final String method = "createCompleteEvent(Pointer)";
        List<ReadOutput310> data = new ArrayList<>();
        ZList list = new ZList(results);
        for (Pointer pointer : list) {
            ReadOutput310 readOutput310 = new ReadOutput310(pointer);
            LOG.debug(method, "readOutput310=" + readOutput310);
            data.add(readOutput310);
        }

        return new ReadCompleteEvent(data);
    }
}
