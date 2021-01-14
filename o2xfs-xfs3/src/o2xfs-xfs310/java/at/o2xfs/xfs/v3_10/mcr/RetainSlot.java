package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RetainSlot extends Struct {

    /**
     * 存储外币包的信息
     */
    protected final LPSTR cardInfo = new LPSTR();

    /**
     * 存储外币包的时间
     */
    protected final LPSTR retainTime = new LPSTR();

    protected RetainSlot() {
        add(cardInfo);
        add(retainTime);
    }

    public RetainSlot(Pointer p) {
        this();
        assignBuffer(p);
    }
    public RetainSlot(RetainSlot copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(RetainSlot copy) {
        cardInfo.set(copy.getCardInfo());
        retainTime.set(copy.getRetainTime());
    }

    public String getCardInfo() {
        return cardInfo.get();
    }

    public String getRetainTime() {
        return retainTime.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RetainSlot that = (RetainSlot) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getCardInfo(), that.getCardInfo())
                .append(getRetainTime(), that.getRetainTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCardInfo())
                .append(getRetainTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cardInfo", getCardInfo())
                .append("retainTime", getRetainTime())
                .toString();
    }
}
