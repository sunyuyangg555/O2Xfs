package at.o2xfs.xfs.service.crd;

import at.o2xfs.xfs.v3_00.crd.CardUnit3;
import at.o2xfs.xfs.v3_00.crd.DevicePosition3;
import at.o2xfs.xfs.v3_00.crd.MediaDetected3;
import at.o2xfs.xfs.v3_00.crd.PowerSaveChange3;

public interface CRDServiceListener {
    void onMediaRemoved();
    void onMediaDetected(MediaDetected3 mediaDetected3);
    void onCardUnitInfoChanged(CardUnit3 cardUnit3);
    void onDevicePosition(DevicePosition3 devicePosition3);
    void onPowerSaveChange(PowerSaveChange3 powerSaveChange3);
}
