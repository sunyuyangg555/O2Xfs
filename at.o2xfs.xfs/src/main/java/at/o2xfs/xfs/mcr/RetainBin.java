package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum RetainBin implements XfsConstant  {

    BINOK(0L),
    BINFULL(3L),
    BINHIGH(4L);

    private final long value;

    RetainBin(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
