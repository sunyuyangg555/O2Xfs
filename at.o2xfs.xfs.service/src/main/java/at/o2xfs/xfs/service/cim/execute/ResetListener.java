package at.o2xfs.xfs.service.cim.execute;


import at.o2xfs.xfs.service.cim.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cim.event.InfoAvailableListener;
import at.o2xfs.xfs.service.cim.event.InputP6Listener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;

public interface ResetListener extends CommandListener<SuccessEvent>,
		CashUnitErrorListener,
		InputP6Listener,
		InfoAvailableListener {
}
