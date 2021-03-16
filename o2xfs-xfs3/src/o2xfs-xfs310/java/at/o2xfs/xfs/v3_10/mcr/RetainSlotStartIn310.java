package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RetainSlotStartIn310 extends Struct {

    /**
     * 需要取出的空的存包格大小，“0”：表示普通格子，“1”：表示加大格子
     */
    protected final LPSTR data = new LPSTR();

    protected RetainSlotStartIn310() {
        add(data);
    }

    public RetainSlotStartIn310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public RetainSlotStartIn310(RetainSlotStartIn310 copy) {
        this();
        allocate();
        set(copy);
    }

    public RetainSlotStartIn310(String data) {
        this();
        allocate();
        this.data.set(data);
    }

    private void set(RetainSlotStartIn310 copy) {
        data.set(copy.getData());
    }

    public String getData() {
        return data.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RetainSlotStartIn310 that = (RetainSlotStartIn310) o;

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
