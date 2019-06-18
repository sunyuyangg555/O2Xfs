package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.xfs.service.cim.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;


public interface SetCashUnitInfoListener extends CommandListener<SuccessEvent>
        , CashUnitErrorListener {

}
