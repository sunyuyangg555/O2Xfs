package at.o2xfs.operator.task.xfs.cim;

import at.o2xfs.operator.task.xfs.XFS;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.cim.ExchangeType;
import at.o2xfs.xfs.cim.Position;
import at.o2xfs.xfs.v3_00.cim.Output3;
import at.o2xfs.xfs.v3_00.cim.Output3Array;
import at.o2xfs.xfs.v3_00.cim.StartEx3;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class CIMStartExchangeTask extends CIMServiceTask {

    @Override
    @SuppressWarnings("unchecked")
    protected void execute() {

        USHORT[] ushorts = new USHORT[]{new USHORT(0)};
        int[] numberCashUnitArray = new int[]{};
        Set<Position> positions = new HashSet<>();
        positions.add(Position.INFRONT);
        Output3 output3 = new Output3.Builder(1, positions, 1).build();
        Output3Array output3Array = new Output3Array(new Output3[]{output3});
        StartEx3 startEx3 = new StartEx3.Builder(ExchangeType.CLEARRECYCLER, 0, numberCashUnitArray, output3).build();

        XFS.CIM.startExchange(service, startEx3)
                .publishOn(Schedulers.elastic())
                .log()
                .timeout(Duration.ofMinutes(5))
                .subscribe(
                        obj -> showUi(obj, CIMStartExchangeTask.class),
                        ex -> showException(ex),
                        () -> appendMessage("SUCCESS")

                );
    }
}
