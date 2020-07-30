package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

public enum PowerOnOption implements XfsConstant {

    NOACTION(1L),
    EJECT(2L),
    RETAIN(3L),
    EJECTTHENRETAIN(4L);

    private final long value;

    private PowerOnOption(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
