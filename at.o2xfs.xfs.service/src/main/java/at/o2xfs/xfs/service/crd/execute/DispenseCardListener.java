package at.o2xfs.xfs.service.crd.execute;

import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.crd.event.CardUnitErrorListener;

public interface DispenseCardListener extends CommandListener<SuccessEvent>,
        CardUnitErrorListener {
}
