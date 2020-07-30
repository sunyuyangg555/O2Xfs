package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum DispenseTo implements XfsConstant {

    CONSUMER(1L),
    TRANSPORT(2L);

    private final long value;

    private DispenseTo(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
