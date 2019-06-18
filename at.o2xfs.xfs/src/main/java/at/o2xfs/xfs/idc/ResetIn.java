package at.o2xfs.xfs.idc;

import at.o2xfs.xfs.XfsConstant;

public enum ResetIn implements XfsConstant {

    RETAIN(3L),

    EJECT(2L),

    NOACTION(1L);



    private final long value;

    private ResetIn(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}

