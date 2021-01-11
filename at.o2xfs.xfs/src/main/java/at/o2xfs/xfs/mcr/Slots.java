package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum Slots implements XfsConstant {

    /**
     * 外币包槽全空
     */
    EMPTY(0L),
    /**
     * 外币包槽有包
     */
    OK(1L),
    /**
     * 外币包槽满
     */
    FULL(2L);

    private final long value;

    Slots(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
