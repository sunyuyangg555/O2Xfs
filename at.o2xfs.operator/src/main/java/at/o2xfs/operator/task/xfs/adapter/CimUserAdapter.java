package at.o2xfs.operator.task.xfs.adapter;

import at.o2xfs.xfs.service.cim.CimUserListener;
import at.o2xfs.xfs.v3_00.cim.CashIn3;
import reactor.core.publisher.FluxSink;

public class CimUserAdapter implements CimUserListener {

    protected FluxSink sink;

    public CimUserAdapter(FluxSink fluxSink) {
        this.sink = fluxSink;
    }

    @Override
    public void onCashUnitThreshold(CashIn3 cashUnit) {
        sink.next(cashUnit);
    }
}
