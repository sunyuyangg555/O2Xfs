package at.o2xfs.xfs.service.mcr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_10.mcr.RetainSlotStartOut;

public class RetainSlotStartExCompleteEvent implements CompleteEvent<RetainSlotStartOut> {

    private final RetainSlotStartOut retainSlotStartOut;

    public RetainSlotStartExCompleteEvent(RetainSlotStartOut retainSlotStartOut) {
        this.retainSlotStartOut = retainSlotStartOut;
    }


    @Override
    public RetainSlotStartOut get() {
        return retainSlotStartOut;
    }
}
