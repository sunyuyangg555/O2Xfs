package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum Shutter implements XfsConstant  {

    FAIL(0L),

    CLOSE(1L),

    OPEN(2L);

    private final long value;

    Shutter(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
