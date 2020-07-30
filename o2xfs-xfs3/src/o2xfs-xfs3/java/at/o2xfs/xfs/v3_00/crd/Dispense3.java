package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.BOOL;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_dispense
 * {
 *  USHORT usNumber;
 *  BOOL bPresent;
 * } WFSCRDDISPENSE, *LPWFSCRDDISPENSE;
 *
 */
public class Dispense3 extends Struct {

    public Dispense3(Builder builder) {
        this();
        allocate();
        number.set(builder.number);
        present.set(builder.present);
    }

    public static class Builder {
        private int number;
        private boolean present;

        public Builder(int number, boolean present) {
            this.number = number;
            this.present = present;
        }

        public Dispense3 build() {
            return new Dispense3(this);
        }
    }

    protected final USHORT number = new USHORT();
    protected final BOOL present = new BOOL();

    public int getNumber() {
        return number.get();
    }

    public boolean isPresent() {
        return present.get();
    }

    protected Dispense3() {
        add(number);
        add(present);
    }

    public Dispense3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Dispense3(Dispense3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(Dispense3 copy) {
        number.set(copy.getNumber());
        present.set(copy.isPresent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Dispense3)) return false;

        Dispense3 dispense3 = (Dispense3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getNumber(), dispense3.getNumber())
                .append(isPresent(), dispense3.isPresent())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getNumber())
                .append(isPresent())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", getNumber())
                .append("present", isPresent())
                .toString();

    }
}
