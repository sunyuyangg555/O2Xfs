package at.o2xfs.xfs.service.pin.event;

import at.o2xfs.xfs.pin.WfsPINKey;

public interface PinKeyListener {
    void onPinKey(final WfsPINKey pinKey);
}
