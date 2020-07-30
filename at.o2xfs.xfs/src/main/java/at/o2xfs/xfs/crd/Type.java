package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum Type implements XfsConstant {

    SUPPLYBIN(1L),
    RETAINBIN(2L);

    private final long value;

    private Type(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
