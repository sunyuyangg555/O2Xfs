package at.o2xfs.operator.task.xfs.adapter;

import at.o2xfs.xfs.XfsError;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.cmd.event.*;
import reactor.core.publisher.FluxSink;

public  class CommandAdapter implements CommandListener<SuccessEvent> {

    protected FluxSink fluxSink;

    public CommandAdapter(FluxSink fluxSink) {
        this.fluxSink = fluxSink;
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
