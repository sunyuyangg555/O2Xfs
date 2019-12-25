package at.o2xfs.xfs.service.bcr;

import at.o2xfs.xfs.v3_10.bcr.DevicePosition310;
import at.o2xfs.xfs.v3_10.bcr.PowerSaveChange310;

public interface BcrServiceListener {

    void onDevicePosition(DevicePosition310 devicePosition310);

    void onPowerSaveChange(PowerSaveChange310 powerSaveChange310);
}
