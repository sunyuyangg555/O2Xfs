package at.o2xfs.xfs.service.pin;

import at.o2xfs.win32.WORD;

public interface PinServiceListener {
    void onInitialization();
    void onIllegalKeyAccess();
    void onCertificateChange(WORD certificateChange);
    void onHsmTDataChanged();
    void onHsmChanged();
    void onDevicePosition();
    void onPowerSaveChange();
}
