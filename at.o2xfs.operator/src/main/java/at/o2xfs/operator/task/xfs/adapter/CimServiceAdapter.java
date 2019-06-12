package at.o2xfs.operator.task.xfs.adapter;

import at.o2xfs.xfs.service.cim.CimServiceListener;
import at.o2xfs.xfs.service.cim.CimUserListener;
import at.o2xfs.xfs.v3_00.cim.CashIn3;
import at.o2xfs.xfs.v3_00.cim.CountsChanged3;
import at.o2xfs.xfs.v3_00.cim.ItemPosition3;
import at.o2xfs.xfs.v3_10.cim.DevicePosition310;
import at.o2xfs.xfs.v3_10.cim.PositionInfo310;
import at.o2xfs.xfs.v3_10.cim.PowerSaveChange310;
import at.o2xfs.xfs.v3_30.cim.ShutterStatusChanged330;
import reactor.core.publisher.FluxSink;

import java.util.Optional;

public class CimServiceAdapter implements CimServiceListener, CimUserListener {

    protected FluxSink sink;

    public CimServiceAdapter(FluxSink fluxSink) {
        this.sink = fluxSink;
    }

    @Override
    public void onSafeDoorOpen() {
        sink.next("SafeDoorOpen");
    }

    @Override
    public void onSafeDoorClosed() {
        sink.next("SafeDoorClosed");
    }

    @Override
    public void onCashUnitInfoChanged(CashIn3 cashUnit) {
        sink.next(cashUnit);
    }

    @Override
    public void onTellerInfoChanged(int tellerId) {

        sink.next("TellerInfoChanged" + tellerId);
    }

    @Override
    public void onItemsTaken(Optional<PositionInfo310> positionInfo) {
        if (positionInfo.isPresent()) {
            sink.next(positionInfo.get());
        }
    }

    @Override
    public void onCountsChanged(CountsChanged3 countsChanged) {
        sink.next(countsChanged);
    }

    @Override
    public void onItemsPresented(Optional<PositionInfo310> positionInfo) {
        if (positionInfo.isPresent()) {
            sink.next(positionInfo.get());
        }
    }

    @Override
    public void onItemsInserted(Optional<PositionInfo310> positionInfo) {
        if (positionInfo.isPresent()) {
            sink.next(positionInfo.get());
        }
    }

    @Override
    public void onMediaDetected(Optional<ItemPosition3> itemPosition) {
        if (itemPosition.isPresent()) {
            sink.next(itemPosition.get());
        }
    }

    @Override
    public void onDevicePosition(DevicePosition310 devicePosition) {
        sink.next(devicePosition);
    }

    @Override
    public void onPowerSaveChange(PowerSaveChange310 powerSaveChange) {
        sink.next(powerSaveChange);
    }

    @Override
    public void onShutterStatusChanged(ShutterStatusChanged330 shutterStatusChanged) {
        sink.next(shutterStatusChanged);
    }

    @Override
    public void onCashUnitThreshold(CashIn3 cashUnit) {

    }
}
