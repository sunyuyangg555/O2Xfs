package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RetainSlotStartOut extends Struct {

    /**
     * 空的存包格的编号
     */
    protected final LPSTR data = new LPSTR();

    protected RetainSlotStartOut() {
        add(data);
    }

    public RetainSlotStartOut(Pointer p) {
        this();
        assignBuffer(p);
    }

    public RetainSlotStartOut(RetainSlotStartOut copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(RetainSlotStartOut copy) {
        data.set(copy.getData());
    }

    public String getData() {
        return data.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RetainSlotStartOut that = (RetainSlotStartOut) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getData(), that.getData())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getData())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("data", getData())
                .toString();
    }
}
