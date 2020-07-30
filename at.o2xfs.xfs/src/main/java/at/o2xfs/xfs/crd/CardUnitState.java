package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum CardUnitState implements XfsConstant {

    OK(0L),
    LOW(1L),
    EMPTY(2L),
    INOP(3L),
    MISSING(4L),
    HIGH(5L),
    FULL(6L),
    UNKNOWN(7L);

    private final long value;

    private CardUnitState(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
