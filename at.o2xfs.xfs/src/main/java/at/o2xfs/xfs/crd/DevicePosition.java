package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

/* values of
    WFSCRDSTATUS.wDevicePosition
    WFSCRDDEVICEPOSITION.wPosition
*/
public enum DevicePosition implements XfsConstant {

    INPOSITION(0L),
    NOTINPOSITION(1L),
    POSUNKNOWN(2L),
    POSNOTSUPP(3L);

    private final long value;

    DevicePosition(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
