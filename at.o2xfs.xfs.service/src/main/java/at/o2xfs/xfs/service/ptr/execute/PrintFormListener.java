package at.o2xfs.xfs.service.ptr.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.ptr.event.*;

public interface PrintFormListener extends CommandListener<SuccessEvent>,
        NoMediaListener,
        MediaInsertedListener,
        FieldErrorListener,
        FieldWarningListener,
        MediaRejectedListener {
}
