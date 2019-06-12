package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import at.o2xfs.xfs.v3_00.cim.CashIn3;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import org.apache.commons.beanutils.BeanMap;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Map;

public abstract class SetCashUnitInfoTask extends CIMServiceTask {

    @Override
    @SuppressWarnings("unchecked")
    protected void execute() {
        CashInfo3 cashInfo3 = new CashInfo3.Builder(getCashIns()).build();
        XFS.CIM.setCashUnitInfo(service, cashInfo3)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, getTaskClass()),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }

    protected abstract CashIn3[] getCashIns();

    protected abstract Class getTaskClass();



}
