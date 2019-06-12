package at.o2xfs.xfs.idc;

import at.o2xfs.xfs.XfsConstant;

public enum ResetOut implements XfsConstant {

    RETAINED(1L),

    EJECTED(2L),

    READPOSITION(3L),

    JAMMED(4L);

    private final long value;

    private ResetOut(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
