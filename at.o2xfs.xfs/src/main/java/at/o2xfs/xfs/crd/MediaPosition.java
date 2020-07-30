package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum MediaPosition implements XfsConstant {
    PRESENT(1L),
    NOTPRESENT(2L),
    JAMMED(3L),
    NOTSUPP(4L),
    UNKNOWN(5L),
    EXITING(6L),
    RETAINED(7L);
    private final long value;

    MediaPosition(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
