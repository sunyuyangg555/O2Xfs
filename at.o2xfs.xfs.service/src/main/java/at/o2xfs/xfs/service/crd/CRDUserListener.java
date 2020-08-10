package at.o2xfs.xfs.service.crd;

import at.o2xfs.xfs.v3_00.crd.CardUnit3;

public interface CRDUserListener {
    void onCardUnitThreshold(CardUnit3 cardUnit3);
}
