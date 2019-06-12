package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMCashInTask extends CIMServiceTask {

    @Override
    @SuppressWarnings("unchecked")
    protected void execute() {
        XFS.CIM.cashIn(service)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMCashInTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }

}
