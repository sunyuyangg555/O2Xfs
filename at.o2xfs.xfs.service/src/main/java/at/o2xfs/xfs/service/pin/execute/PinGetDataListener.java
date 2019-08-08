package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.pin.event.EnterDataListener;
import at.o2xfs.xfs.service.pin.event.PinKeyListener;

public interface PinGetDataListener extends EnterDataListener, PinKeyListener, CommandListener<PinGetDataCompleteEvent> {
}
