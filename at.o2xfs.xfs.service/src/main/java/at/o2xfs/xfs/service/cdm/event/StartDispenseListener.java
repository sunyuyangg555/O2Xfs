package at.o2xfs.xfs.service.cdm.event;

import at.o2xfs.xfs.type.RequestId;

public interface StartDispenseListener {
    void onStartDispense(RequestId requestId);
}
