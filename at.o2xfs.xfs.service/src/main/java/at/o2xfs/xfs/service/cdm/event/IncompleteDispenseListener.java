package at.o2xfs.xfs.service.cdm.event;

import at.o2xfs.xfs.v3_00.cdm.Denomination3;

public interface IncompleteDispenseListener {
    void onIncompleteDispense(Denomination3 denomination3);
}
