package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_cardunit
 * {
 *  USHORT usNumber;
 *  LPSTR lpszCardName;
 *  USHORT usType;
 *  ULONG ulInitialCount;
 *  ULONG ulCount;
 *  ULONG ulRetainCount;
 *  ULONG ulThreshold;
 *  USHORT usStatus;
 *  BOOL bHardwareSensor;
 * } WFSCRDCARDUNIT, *LPWFSCRDCARDUNIT;
 *
 */
public class CardUnit3 extends Struct {

    public CardUnit3(Builder builder) {
        this();
        allocate();
        number.set(builder.number);
        cardName.set(builder.cardName);
        type.set(builder.type);
        initialCount.set(builder.initialCount);
        count.set(builder.count);
        retainCount.set(builder.retainCount);
        threshold.set(builder.threshold);
        status.set(builder.status);
        hardwareSensor.set(builder.hardwareSensor);
    }

    public static class Builder {
        private final int number;
        private final String cardName;
        private final int type;
        private final long initialCount;
        private final long count;
        private final long retainCount;
        private final long threshold;
        private final int status;
        private final boolean hardwareSensor;


        public Builder(int number, String cardName, int type, long initialCount, long count, long retainCount, long threshold, int status, boolean hardwareSensor) {
            this.number = number;
            this.cardName = cardName;
            this.type = type;
            this.initialCount = initialCount;
            this.count = count;
            this.retainCount = retainCount;
            this.threshold = threshold;
            this.status = status;
            this.hardwareSensor = hardwareSensor;
        }

        public CardUnit3 build() {
            return new CardUnit3(this);
        }
    }

    protected final USHORT number = new USHORT();
    protected final LPSTR cardName = new LPSTR();
    protected final USHORT type = new USHORT();
    protected final ULONG initialCount = new ULONG();
    protected final ULONG count = new ULONG();
    protected final ULONG retainCount = new ULONG();
    protected final ULONG threshold = new ULONG();
    protected final USHORT status = new USHORT();
    protected final BOOL hardwareSensor = new BOOL();

    protected CardUnit3() {
        add(number);
        add(cardName);
        add(type);
        add(initialCount);
        add(count);
        add(retainCount);
        add(threshold);
        add(status);
        add(hardwareSensor);
    }

    public CardUnit3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CardUnit3(CardUnit3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(CardUnit3 copy) {
        number.set(copy.getNumber());
        cardName.set(copy.getCardName());
        type.set(copy.getType());
        initialCount.set(copy.getInitialCount());
        count.set(copy.getCount());
        retainCount.set(copy.getRetainCount());
        threshold.set(copy.getThreshold());
        status.set(copy.getStatus());
        hardwareSensor.set(copy.isHardwareSensor());
    }

    public int getNumber() {
        return number.get();
    }

    public String getCardName() {
        return cardName.get();
    }

    public int getType() {
        return type.get();
    }

    public long getInitialCount() {
        return initialCount.get();
    }

    public long getCount() {
        return count.get();
    }

    public long getRetainCount() {
        return retainCount.get();
    }

    public long getThreshold() {
        return threshold.get();
    }

    public int getStatus() {
        return status.get();
    }

    public boolean isHardwareSensor() {
        return hardwareSensor.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CardUnit3)) return false;

        CardUnit3 cardUnit = (CardUnit3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getNumber(), cardUnit.getNumber())
                .append(getCardName(), cardUnit.getCardName())
                .append(getType(), cardUnit.getType())
                .append(getInitialCount(), cardUnit.getInitialCount())
                .append(getCount(), cardUnit.getCount())
                .append(getRetainCount(), cardUnit.getRetainCount())
                .append(getThreshold(), cardUnit.getThreshold())
                .append(getStatus(), cardUnit.getStatus())
                .append(isHardwareSensor(), cardUnit.isHardwareSensor())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getNumber())
                .append(getCardName())
                .append(getType())
                .append(getInitialCount())
                .append(getCount())
                .append(getRetainCount())
                .append(getThreshold())
                .append(getStatus())
                .append(isHardwareSensor())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", getNumber())
                .append("cardName", getCardName())
                .append("type", getType())
                .append("initialCount", getInitialCount())
                .append("count", getCount())
                .append("retainCount", getRetainCount())
                .append("threshold", getThreshold())
                .append("status", getStatus())
                .append("hardwareSensor", isHardwareSensor())
                .toString();

    }
}
