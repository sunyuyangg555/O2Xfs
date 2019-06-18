package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.adapter.CommandAdapter;
import at.o2xfs.xfs.service.cim.execute.CashInStartListener;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import reactor.core.publisher.FluxSink;

public class CashInStartAdapter extends CommandAdapter implements CashInStartListener {
    public CashInStartAdapter(FluxSink fluxSink) {
        super(fluxSink);
    }

    @Override
    public void onComplete(SuccessEvent event) {
        fluxSink.complete();
    }
}
