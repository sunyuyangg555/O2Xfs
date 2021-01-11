package at.o2xfs.xfs.service.ptr.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.ptr.event.FieldErrorListener;
import at.o2xfs.xfs.service.ptr.event.MediaInsertedListener;
import at.o2xfs.xfs.service.ptr.event.MediaRejectedListener;
import at.o2xfs.xfs.service.ptr.event.NoMediaListener;

public interface ReadFormListener extends
        CommandListener<ReadFormOutEvent>,
        NoMediaListener,
        MediaInsertedListener,
        FieldErrorListener,
        MediaRejectedListener {
}
