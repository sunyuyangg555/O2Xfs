package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.xfs.cim.CashInItemType;
import at.o2xfs.xfs.cim.CashInType;
import at.o2xfs.xfs.v3_00.cim.CashIn3;


public class SetCashUnitInfo1Cin4RecFilled extends SetCashUnitInfoTask {

    @Override
    protected CashIn3[] getCashIns() {

        return new CashIn3[]{
                createCashIn3(1, "1A", 10, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(2, "2A", 10, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(3, "3A", 10, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
                createCashIn3(4, "4A", 0,  false, CashInType.RETRACTCASSETTE, CashInItemType.ALL),
                createCashIn3(5, "5A", 10, false, CashInType.RECYCLING, CashInItemType.INDIVIDUAL),
        };
    }

    @Override
    protected Class getTaskClass() {
        return SetCashUnitInfo1Cin4RecFilled.class;
    }
}
