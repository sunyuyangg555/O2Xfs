package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_10.mcr.EjectSlot;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartOut;

public class EjectSlotStartCompleteEvent implements CompleteEvent<EjectSlot> {

    private final EjectSlot ejectSlot;

    public EjectSlotStartCompleteEvent(EjectSlot ejectSlot) {
        this.ejectSlot = ejectSlot;
    }


    @Override
    public EjectSlot get() {
        return ejectSlot;
    }
}
