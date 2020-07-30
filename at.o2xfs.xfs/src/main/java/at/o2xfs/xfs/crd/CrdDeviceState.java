package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;
import at.o2xfs.xfs.XfsDeviceState;

public enum CrdDeviceState implements XfsConstant  {

    ONLINE(XfsDeviceState.ONLINE.getValue()),
    OFFLINE(XfsDeviceState.OFFLINE.getValue()),
    POWEROFF(XfsDeviceState.POWEROFF.getValue()),
    NODEVICE(XfsDeviceState.NODEVICE.getValue()),
    HWERROR(XfsDeviceState.HWERROR.getValue()),
    USERERROR(XfsDeviceState.USERERROR.getValue()),
    BUSY(XfsDeviceState.BUSY.getValue()),
    FRAUDATTEMPT(XfsDeviceState.FRAUDATTEMPT.getValue());

    private final long value;

    CrdDeviceState(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
