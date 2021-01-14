package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SlotsInfo310 extends Struct {
    protected final USHORT count = new USHORT();
    protected final Pointer list = new Pointer();

    protected SlotsInfo310() {
        add(count);
        add(list);
    }

    public SlotsInfo310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public SlotsInfo310( SlotsInfo310 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(SlotsInfo310 copy) {
        count.set(count.get());
        list.pointTo(new SlotUnitInfo310Array(copy.getList()));
    }

    public int getCount() {
        return count.get();
    }

    public SlotUnitInfo310[] getList() {
        return new SlotUnitInfo310Array(list, getCount()).get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SlotsInfo310 that = (SlotsInfo310) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getCount(), that.getCount())
                .append(getList(), that.getList())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCount())
                .append(getList())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("count", getCount())
                .append("list", getList())
                .toString();
    }
}
