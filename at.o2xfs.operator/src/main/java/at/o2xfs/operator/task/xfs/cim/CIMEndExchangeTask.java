package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import at.o2xfs.xfs.cim.CashInItemType;
import at.o2xfs.xfs.cim.CashInType;
import at.o2xfs.xfs.v3_00.cim.CashIn3;
import at.o2xfs.xfs.v3_00.cim.CashInfo3;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class CIMEndExchangeTask extends CIMServiceTask {

    @Override
    @SuppressWarnings("unchecked")
    protected void execute() {

        CashIn3[] cashIns = new CashIn3[]{
                createCashIn3(1, "1A", 0, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(2, "2A", 0, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(3, "3A", 0, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(4, "4A", 0, false, CashInType.RETRACTCASSETTE, CashInItemType.ALL),
                createCashIn3(5, "5A", 0, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
        };
        CashInfo3 cashInfo3 = new CashInfo3.Builder(cashIns).build();
        XFS.CIM.endExchange(service, cashInfo3)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMEndExchangeTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }
}
