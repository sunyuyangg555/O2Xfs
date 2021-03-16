package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RetainSlot310 extends Struct {

    public RetainSlot310(Builder builder) {
        this();
        allocate();
        cardInfo.set(builder.cardInfo);
        retainTime.set(builder.retainTime);
    }

    public static class Builder {
        private final String cardInfo;
        private final String retainTime;

        public Builder(String cardInfo, String retainTime) {
            this.cardInfo = cardInfo;
            this.retainTime = retainTime;
        }

        public RetainSlot310 build() {
            return new RetainSlot310(this);
        }
    }

    /**
     * 存储外币包的信息
     */
    protected final LPSTR cardInfo = new LPSTR();

    /**
     * 存储外币包的时间
     */
    protected final LPSTR retainTime = new LPSTR();

    protected RetainSlot310() {
        add(cardInfo);
        add(retainTime);
    }

    public RetainSlot310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public RetainSlot310(RetainSlot310 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(RetainSlot310 copy) {
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

        RetainSlot310 that = (RetainSlot310) o;

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
