package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum Transport implements XfsConstant {

    OK(0L),
    INOP(1L),
    UNKNOWN(2L),
    NOTSUPPORTED(3L);

    private final long value;

    private Transport(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
