package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_cu_info
 * {
 *   USHORT usCount;
 *   LPWFSCRDCARDUNIT *lppList;
 * } WFSCRDCUINFO, *LPWFSCRDCUINFO;
 *
 */
public class CardUnitInfo3 extends Struct {
    protected final USHORT count = new USHORT();
    protected final Pointer list = new Pointer();

    protected CardUnitInfo3() {
        add(count);
        add(list);
    }

    public CardUnitInfo3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CardUnitInfo3(CardUnitInfo3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(CardUnitInfo3 copy) {
        count.set(copy.getCount());
        list.pointTo(new CardUnit3Array(copy.getList()));
    }

    public int getCount() {
        return count.get();
    }

    public CardUnit3[] getList() {
        return new CardUnit3Array(list, getCount()).get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CardUnitInfo3)) return false;

        CardUnitInfo3 that = (CardUnitInfo3) o;

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
