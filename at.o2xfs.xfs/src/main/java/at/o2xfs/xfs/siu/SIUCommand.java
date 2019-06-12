package at.o2xfs.xfs.siu;

import at.o2xfs.xfs.XfsConstant;

public enum SIUCommand implements XfsConstant {

    OFF(0x0001L),
    ON(0x0002L),
    SLOWFLASH(0x0004L),
    MEDIUMFLASH(0x0008L),
    QUICKFLASH(0x0010L),
    CONTINUOUS(0x0080L);

    private final long value;

    private SIUCommand(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
