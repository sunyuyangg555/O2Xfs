package at.o2xfs.xfs.service.bcr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_10.bcr.ReadOutput310;

import java.util.List;

public class ReadCompleteEvent implements CompleteEvent<List<ReadOutput310>> {

    private final List<ReadOutput310> readOutput310s;

    public ReadCompleteEvent(List<ReadOutput310> readOutput310s) {
        this.readOutput310s = readOutput310s;
    }


    @Override
    public List<ReadOutput310> get() {
        return readOutput310s;
    }
}
