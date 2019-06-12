package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.adapter.CimExeAdapter;
import at.o2xfs.xfs.XfsError;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.cim.cmd.CashInRollbackCompleteEvent;
import at.o2xfs.xfs.service.cim.cmd.CashInRollbackListener;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import org.apache.commons.beanutils.BeanMap;
import reactor.core.publisher.FluxSink;

import java.util.Map;

public class CashInRollbackAdapter extends CimExeAdapter implements CashInRollbackListener {
    public CashInRollbackAdapter(FluxSink fluxSink) {
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
    public void onComplete(CashInRollbackCompleteEvent event) {
        if (event.getCashInfo().isPresent()) {
            CashInfo3 cashInfo3 = event.getCashInfo().get();
            Map map = new BeanMap(cashInfo3);
            fluxSink.next(map);
        }
    }
}
