package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_reset
 * {
 *  USHORT usAction;
 * } WFSCRDRESET, *LPWFSCRDRESET;
 */
public class Reset3 extends Struct {

    public Reset3(Builder builder) {
        this();
        allocate();
        action.set(builder.action);
    }

    public static class Builder{
        private int action;

        public Builder(int action) {
            this.action = action;
        }

        public Reset3 build() {
            return new Reset3(this);
        }
    }

    protected final USHORT action = new USHORT();

    protected Reset3() {
        add(action);
    }

    public Reset3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Reset3(Reset3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(Reset3 copy) {
        action.set(copy.getAction());
    }

    public int getAction() {
        return action.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Reset3)) return false;

        Reset3 reset3 = (Reset3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getAction(), reset3.getAction())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getAction())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("action", getAction())
                .toString();
    }
}
