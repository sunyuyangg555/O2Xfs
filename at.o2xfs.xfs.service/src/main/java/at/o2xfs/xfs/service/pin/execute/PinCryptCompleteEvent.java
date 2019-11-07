package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.service.cmd.event.CompleteEvent;

public class PinCryptCompleteEvent implements CompleteEvent<WfsXData> {

    private final WfsXData wfsXData;

    public PinCryptCompleteEvent(WfsXData wfsXData) {
        this.wfsXData = wfsXData;
    }


    @Override
    public WfsXData get() {
        return wfsXData;
    }
}
