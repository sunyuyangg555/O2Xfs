package at.o2xfs.xfs.service.cim.event;

import at.o2xfs.xfs.cim.Reason;

public interface InputRefuseListener {
    void onInputRefuse(Reason reason);
}
