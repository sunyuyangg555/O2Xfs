package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.adapter.CimExeAdapter;
import at.o2xfs.xfs.XfsError;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.cim.execute.RetractCompleteEvent;
import at.o2xfs.xfs.service.cim.execute.RetractListener;
import at.o2xfs.xfs.service.cmd.event.CancelEvent;
import at.o2xfs.xfs.service.cmd.event.ErrorEvent;
import org.apache.commons.beanutils.BeanMap;
import reactor.core.publisher.FluxSink;

import java.util.Map;

public class RetractAdapter extends CimExeAdapter implements RetractListener {


    public RetractAdapter(FluxSink fluxSink) {
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
    public void onComplete(RetractCompleteEvent event) {
        if (event.getCashInfo().isPresent()) {
            Map map = new BeanMap(event.getCashInfo().get());
            fluxSink.next(map);
        }
    }
}
