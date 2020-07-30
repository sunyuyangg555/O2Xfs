package at.o2xfs.xfs.crd;

import at.o2xfs.xfs.XfsConstant;

/*values of WFSCRDCUERROR.wFailure */
public enum Failure implements XfsConstant {

    EMPTY(1L),
    ERROR(2L),
    INVALID(3L);

    private final long value;

    private Failure(final long value) {
        this.value = value;
    }

    @Override
    public long getValue() {
        return value;
    }
}
