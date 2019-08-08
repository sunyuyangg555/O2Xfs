package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.xfs.pin.WfsPINData;
import at.o2xfs.xfs.service.cmd.event.CompleteEvent;

public class PinGetDataCompleteEvent implements CompleteEvent<WfsPINData> {

    private WfsPINData pinData;

    public PinGetDataCompleteEvent(WfsPINData pinData) {
        this.pinData = pinData;
    }

    @Override
    public WfsPINData get() {
        return pinData;
    }
}
