package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_retain_card
 * {
 *  USHORT usNumber;
 * } WFSCRDRETAINCARD, *LPWFSCRDRETAINCARD;
 */
public class RetainCard3 extends Struct {

    public RetainCard3(Builder builder) {
        this();
        allocate();
        number.set(builder.number);
    }

    public static class Builder {
        private int number;
        public Builder(int number) {
            this.number = number;
        }

        public RetainCard3 build() {
            return new RetainCard3(this);
        }
    }

    protected USHORT number = new USHORT();

    protected RetainCard3() {
        add(number);
    }

    public RetainCard3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public RetainCard3(RetainCard3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(RetainCard3 copy) {
        number.set(copy.getNumber());
    }

    public int getNumber() {
        return number.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RetainCard3)) return false;

        RetainCard3 that = (RetainCard3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getNumber(), that.getNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getNumber())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", getNumber())
                .toString();
    }
}
