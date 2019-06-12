package at.o2xfs.xfs.service.cim.cmd;


import at.o2xfs.xfs.service.cim.cmd.listener.CashUnitErrorListener;
import at.o2xfs.xfs.service.cim.cmd.listener.NoteErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;


public interface StartExchangeListener extends CommandListener<StartExchangeCompleteEvent>, CashUnitErrorListener {

}
