package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum Shutter implements XfsConstant {

    CLOSED(0L),
    OPEN(1L),
    JAMMED(2L),
    UNKNOWN(3L),
    NOTSUPPORTED(4L);

    private final long value;

    private Shutter(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
