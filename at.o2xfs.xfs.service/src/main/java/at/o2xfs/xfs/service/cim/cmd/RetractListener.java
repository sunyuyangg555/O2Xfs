package at.o2xfs.xfs.service.cim.cmd;

import at.o2xfs.xfs.service.cim.cmd.listener.CashUnitErrorListener;
import at.o2xfs.xfs.service.cim.cmd.listener.InfoAvailableListener;
import at.o2xfs.xfs.service.cim.cmd.listener.InputP6Listener;
import at.o2xfs.xfs.service.cim.cmd.listener.NoteErrorListener;
import at.o2xfs.xfs.service.cmd.event.CommandListener;



public interface RetractListener extends
        CommandListener<RetractCompleteEvent>,
        CashUnitErrorListener,
        NoteErrorListener,
        InputP6Listener,
        InfoAvailableListener {

}
