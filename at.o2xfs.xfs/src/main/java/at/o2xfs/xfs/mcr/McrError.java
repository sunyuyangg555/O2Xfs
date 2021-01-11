package at.o2xfs.xfs.mcr;

import at.o2xfs.xfs.XfsConstant;

public enum McrError implements XfsConstant {

    NOMEDIA(-251L),
    MEDIAEXIST(-252),
    MEDIAJAM(-253),
    SLOTFULL(-254),
    SLOTNOTEXIST(-255),
    SHUTTERBLOCKED(-256),
    SHUTTERCLOSEFAILED(-257),
    SHUTTEROPENFAILED(-258),
    CODENOTMATCH(-259),
    CAPTUREIMPURITY(-260);

    private final long value;

    McrError(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
