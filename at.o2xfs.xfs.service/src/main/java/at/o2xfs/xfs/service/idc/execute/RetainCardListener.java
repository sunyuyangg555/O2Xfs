package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.idc.event.MediaRetainedListener;

public interface RetainCardListener extends MediaRetainedListener, CommandListener<RetainCardCompleteEvent> {
}
