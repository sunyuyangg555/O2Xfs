package at.o2xfs.xfs.service.idc.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.idc.RetainCard3;

public class RetainCardCompleteEvent implements CompleteEvent<RetainCard3> {

    private RetainCard3 retainCard3;

    public RetainCardCompleteEvent(RetainCard3 retainCard3) {
        this.retainCard3 = retainCard3;
    }

    @Override
    public RetainCard3 get() {
        return retainCard3;
    }
}
