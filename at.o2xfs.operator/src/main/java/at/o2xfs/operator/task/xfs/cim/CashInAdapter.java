package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.adapter.CimExeAdapter;
import at.o2xfs.xfs.XfsError;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.cim.execute.CashInCompleteEvent;
import at.o2xfs.xfs.service.cim.execute.CashInListener;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import at.o2xfs.xfs.v3_00.cim.NoteNumberList3;
import reactor.core.publisher.FluxSink;

import java.util.HashMap;
import java.util.Map;

public class CashInAdapter extends CimExeAdapter implements CashInListener {

    public CashInAdapter(FluxSink fluxSink) {
        super(fluxSink);
    }

    @Override
    public void onCancel(CancelEvent event) {
        fluxSink.error(XfsException.createFor(XfsError.CANCELED.getValue()));
    }

    @Override
    public void onError(ErrorEvent event) {
        fluxSink.error(XfsException.createFor(XfsError.SOFTWARE_ERROR.getValue()));
    }

    @Override
    public void onComplete(CashInCompleteEvent event) {
        Map map = new HashMap();

        if (event.getNoteNumberList().isPresent()) {
            NoteNumberList3 noteNumberList3 = event.getNoteNumberList().get();
            map.put("NoteNumber", noteNumberList3.getNoteNumber().toString());
            map.put("NumOfNoteNumbers", noteNumberList3.getNumOfNoteNumbers() + "");
            fluxSink.next(map);
        }
    }
}
