package at.o2xfs.operator.task.xfs.adapter;

import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.cim.CimError;
import at.o2xfs.xfs.cim.Reason;
import at.o2xfs.xfs.service.cim.cmd.listener.*;
import at.o2xfs.xfs.v3_00.cim.CashUnitError3;
import at.o2xfs.xfs.v3_00.cim.NoteNumberList3;
import at.o2xfs.xfs.v3_00.cim.P6Info3;
import at.o2xfs.xfs.v3_10.cim.ItemInfoSummary310;
import reactor.core.publisher.FluxSink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CimExeAdapter
        implements CashUnitErrorListener,
        InputRefuseListener,
        InsertItemsListener,
        SubCashInListener,
        NoteErrorListener,
        InputP6Listener,
        InfoAvailableListener {

    protected FluxSink fluxSink;

    public CimExeAdapter(FluxSink fluxSink) {
        //super(fluxSink);
        this.fluxSink = fluxSink;
    }


    @Override
    public void onCashUnitError(CashUnitError3 cashUnitError) {
        fluxSink.error(XfsException.createFor(CimError.CASHUNITERROR.getValue()));
    }

    @Override
    public void onInfoAvailable(List<ItemInfoSummary310> itemInfoSummary310s) {
        fluxSink.next(itemInfoSummary310s);
    }

    @Override
    public void onInputP6(List<P6Info3> p6Info3s) {
        fluxSink.next(p6Info3s);
    }

    @Override
    public void onInputRefuse(Reason reason) {
        fluxSink.error(XfsException.createFor(reason.getValue()));
    }

    @Override
    public void onInsertItems() {
        Map map = new HashMap();
        map.put("onInsertItems", "the device is ready to start accepting media");
        fluxSink.next(map);
    }

    @Override
    public void onNoteError(Reason reason) {
        fluxSink.error(XfsException.createFor(reason.getValue()));
    }

    @Override
    public void onSubCashIn(NoteNumberList3 noteNumberList3) {
        fluxSink.next(noteNumberList3);
    }
}
