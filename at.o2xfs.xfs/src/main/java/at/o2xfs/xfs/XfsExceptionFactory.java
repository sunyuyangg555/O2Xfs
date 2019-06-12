package at.o2xfs.xfs;

public interface XfsExceptionFactory {

    void throwException(final long errorCode) throws XfsException;

    default XfsException createException(final long errorCode) {
        try {
            throwException(errorCode);
        } catch (XfsException e) {
            return e;
        }
        return null;
    }
}
