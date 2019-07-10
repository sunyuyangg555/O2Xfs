package at.o2xfs.xfs.service.cdm.event;

import at.o2xfs.xfs.v3_30.cdm.IncompleteRetract330;

public interface IncompleteRetractListener {
    void onIncompleteRetract(IncompleteRetract330 incompleteRetract);
}
