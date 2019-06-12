package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMGetItemInfoTask extends CIMServiceTask {
    @Override
    protected void execute() {
        XFS.CIM.getItemInfo(service)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMGetItemInfoTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }
}
