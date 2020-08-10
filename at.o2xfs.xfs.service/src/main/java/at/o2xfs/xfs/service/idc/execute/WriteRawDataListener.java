package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.idc.event.InsertCardListener;
import at.o2xfs.xfs.service.idc.event.InvalidMediaListener;
import at.o2xfs.xfs.service.ptr.event.MediaInsertedListener;

public interface WriteRawDataListener extends MediaInsertedListener,
        InvalidMediaListener,
        InsertCardListener, CommandListener<SuccessEvent> {
}
