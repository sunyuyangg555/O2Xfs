package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.adapter.CimExeAdapter;
import at.o2xfs.xfs.XfsError;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.cim.execute.EndExchangeListener;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import reactor.core.publisher.FluxSink;

public class EndExchangeAdapter extends CimExeAdapter implements EndExchangeListener {
    public EndExchangeAdapter(FluxSink fluxSink) {
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
    public void onComplete(SuccessEvent event) {
        fluxSink.next("Complete");
    }
}
