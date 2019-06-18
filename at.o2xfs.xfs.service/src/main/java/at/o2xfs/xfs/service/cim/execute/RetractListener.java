package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.xfs.service.cim.event.CashUnitErrorListener;
import at.o2xfs.xfs.service.cim.event.InfoAvailableListener;
import at.o2xfs.xfs.service.cim.event.InputP6Listener;
import at.o2xfs.xfs.service.cim.event.NoteErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;



public interface RetractListener extends
        CommandListener<RetractCompleteEvent>,
        CashUnitErrorListener,
        NoteErrorListener,
        InputP6Listener,
        InfoAvailableListener {

}
