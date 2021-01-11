package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

/**
 * MCR Public Info Commands
 */
public enum McrInfoCommand implements XfsConstant {

    /*
     * @since v3.10
     */
    CAPABILITIES(251L),

    /*
     * @since v3.10
     */
    STATUS(252L),

    /*
     * @since v3.10
     */
    SLOTS_INFO(253L);

    private final long value;

    McrInfoCommand(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
