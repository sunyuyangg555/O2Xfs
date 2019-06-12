package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMCashInStartTask extends CIMServiceTask{


    @Override
    @SuppressWarnings("unchecked")
    protected void execute() {
        XFS.CIM.cashInStart(service)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMCashInStartTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }


}
