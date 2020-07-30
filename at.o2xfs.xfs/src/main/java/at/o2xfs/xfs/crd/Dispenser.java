package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum Dispenser implements XfsConstant {

    OK(0L),
    STATE(1L),
    STOP(2L),
    UNKNOW(3L);

    private final long value;

    Dispenser(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
