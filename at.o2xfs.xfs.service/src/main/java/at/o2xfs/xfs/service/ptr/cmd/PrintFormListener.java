package at.o2xfs.xfs.service.ptr.cmd;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.ptr.cmd.listener.*;

public interface PrintFormListener extends CommandListener<SuccessEvent>,
        NoMediaListener,
        MediaInsertedListener,
        FieldErrorListener,
        FieldWarningListener,
        MediaRejectedListener {
}
