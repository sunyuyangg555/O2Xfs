package at.o2xfs.xfs.service;

import at.o2xfs.xfs.*;

public interface XfsServiceSystemListener {

    void onUndeliverableMessage(WfsUndeliverableMessage undeliverableMessage);

    void onVersionError(WfsVersionError versionError);

    void onDevStatus(WfsDevStatus status);

    void onAppDisconnect(WfsAppDisconnect appDisconnect);

    void onHardwareError(WfsHardwareError2 hwError);
    void onSoftwareError(WfsHardwareError2 hwError);
    void onUserError(WfsHardwareError2 hwError);
    void onFraudAttemptError(WfsHardwareError2 hwError);

    void onLockRequested();
}
