package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartOut310;

public class RetainSlotStartExCompleteEvent implements CompleteEvent<RetainSlotStartOut310> {

    private final RetainSlotStartOut310 retainSlotStartOut310;

    public RetainSlotStartExCompleteEvent(RetainSlotStartOut310 retainSlotStartOut310) {
        this.retainSlotStartOut310 = retainSlotStartOut310;
    }


    @Override
    public RetainSlotStartOut310 get() {
        return retainSlotStartOut310;
    }
}
