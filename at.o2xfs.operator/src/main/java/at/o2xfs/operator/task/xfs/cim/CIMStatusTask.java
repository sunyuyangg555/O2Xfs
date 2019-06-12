package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.operator.task.xfs.XFS;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMStatusTask extends CIMServiceTask {

    private static final Logger LOG = LoggerFactory.getLogger(CIMStatusTask.class);

    @Override
    protected void execute() {

        String method = "execute()";

        XFS.CIM.status(service)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMStatusTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }
}
