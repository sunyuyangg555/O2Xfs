package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMGetP6InfoTask extends CIMServiceTask {
    @Override
    protected void execute() {
        XFS.CIM.getP6Info(service)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMGetP6InfoTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }
}
