package at.o2xfs.xfs.service.cdm.execute;


import at.o2xfs.xfs.service.cdm.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;


public interface EndExchangeListener extends CommandListener<SuccessEvent>, CashUnitErrorListener {

}
