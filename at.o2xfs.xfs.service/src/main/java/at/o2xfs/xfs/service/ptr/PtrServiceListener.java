package at.o2xfs.xfs.service.ptr;

import at.o2xfs.xfs.v3_00.ptr.BinThreshold3;
import at.o2xfs.xfs.v3_00.ptr.MediaDetected3;
import at.o2xfs.xfs.v3_10.ptr.*;

public interface PtrServiceListener {

    void onMediaTaken();

    void onMediaInserted();

    void onMediaDetected(MediaDetected3 mediaDetected3);

    void onRetractBinStatus(BinThreshold3 binThreshold3);

    void onDefinitionLoaded(DefinitionLoaded310 definitionLoaded310);

    void onMediaPresented(MediaPresented310 mediaPresented310);

    void onMediaAutoRetracted(MediaRetracted310 mediaRetracted310);

    void onDevicePosition(DevicePosition310 devicePosition310);

    void onPowerSaveChange(PowerSaveChange310 powerSaveChange310);
}
