package at.o2xfs.xfs.service.siu.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.siu.event.PortErrorListener;

public interface SetGuidLightListener extends PortErrorListener, CommandListener<SuccessEvent> {
}
