package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

/* XFS CRD Errors */
public enum CrdError implements XfsConstant {

    MEDIAJAM(1400L),
    NOMEDIA(1401L),
    MEDIARETAINED(1402L),
    RETAINBINFULL(1403L),

    SHUTTERFAIL(1404L),
    DEVICE_OCCUPIED(1405L),
    CARDUNITERROR(1406L),
    INVALIDCARDUNIT(1407L),

    INVALID_PORT(1408L),
    INVALIDRETAINBIN(1409L),
    POWERSAVETOOSHORT(1410L),
    POWERSAVEMEDIAPRESENT(1411L);

    private final long value;

    private CrdError(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
