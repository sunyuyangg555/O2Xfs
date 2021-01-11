package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum Type implements XfsConstant  {

    SLOTID(0L),

    CARDDATA(1L);

    private final long value;

    private Type(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
