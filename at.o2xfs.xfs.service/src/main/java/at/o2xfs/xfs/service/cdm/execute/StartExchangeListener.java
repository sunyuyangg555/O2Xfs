package at.o2xfs.xfs.service.cdm.execute;

import at.o2xfs.xfs.service.cdm.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;


public interface StartExchangeListener extends CommandListener<StartExchangeCompleteEvent>, CashUnitErrorListener {

}
