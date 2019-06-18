package at.o2xfs.xfs.service.cim.execute;


import at.o2xfs.xfs.service.cim.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;


public interface StartExchangeListener extends CommandListener<StartExchangeCompleteEvent>, CashUnitErrorListener {

}
