package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.mcr.SlotUnitInfoStatus;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SlotUnitInfo310 extends Struct {
    /**
     * 外币包槽编号,从1开始
     */
    protected final USHORT number = new USHORT();
    protected final XfsWord<SlotUnitInfoStatus> status = new XfsWord<>(SlotUnitInfoStatus.class);
    /**
     * 此包是否被超时忘取回收过
     */
    protected final USHORT isRetained = new USHORT();
    /**
     * 保存的外币包信息（如二维码信息）
     */
    protected final LPSTR cardInfo = new LPSTR();

    /**
     *超时忘取回收时扫描到的封包信息
     */
    protected final LPSTR retainCardInfo = new LPSTR();

    /**
     * 全盘扫描时间，时间格式统一为YYYYMMDDHHMMSS
     */
    protected final LPSTR restoreTime = new LPSTR();

    /**
     * 此包被回收的时间，如没被回收，该字段为空;时间格式统一为
     * YYYYMMDDHHMMSS
     */
    protected final LPSTR retainTime = new LPSTR();

    /**
     * 外币包存储位置，顺序为左上往右下，如第一个槽的位置为：”A01”;
     */
    protected final LPSTR position = new LPSTR();

    protected SlotUnitInfo310() {
        add(number);
        add(status);
        add(isRetained);
        add(cardInfo);
        add(retainCardInfo);
        add(restoreTime);
        add(retainTime);
        add(position);
    }

    public SlotUnitInfo310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public SlotUnitInfo310(SlotUnitInfo310 copy) {
        this();
        allocate();
        set(copy);
    }

    public SlotUnitInfo310(Builder builder) {
        this();
        allocate();
        set(builder);
    }

    private void set(Builder builder) {
        number.set(builder.number);
        status.set(builder.status);
        isRetained.set(builder.isRetained);
        cardInfo.set(builder.cardInfo);
        retainCardInfo.set(builder.retainCardInfo);
        restoreTime.set(builder.restoreTime);
        retainTime.set(builder.retainTime);
        position.set(builder.position);
    }

    protected void set(SlotUnitInfo310 copy) {
        number.set(copy.getNumber());
        status.set(copy.getStatus());
        isRetained.set(copy.isRetained());
        cardInfo.set(copy.getCardInfo());
        retainCardInfo.set(copy.getRetainCardInfo());
        restoreTime.set(copy.getRestoreTime());
        retainTime.set(copy.getRetainTime());
        position.set(copy.getPosition());
    }

    public int getNumber() {
        return number.get();
    }

    public SlotUnitInfoStatus getStatus() {
        return status.get();
    }

    public int isRetained() {
        return isRetained.get();
    }

    public String getCardInfo() {
        return cardInfo.get();
    }

    public String getRetainCardInfo() {
        return retainCardInfo.get();
    }

    public String getRestoreTime() {
        return restoreTime.get();
    }

    public String getRetainTime() {
        return retainTime.get();
    }

    public String getPosition() {
        return position.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SlotUnitInfo310 that = (SlotUnitInfo310) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getNumber(), that.getNumber())
                .append(getStatus(), that.getStatus())
                .append(isRetained(), that.isRetained())
                .append(getCardInfo(), that.getCardInfo())
                .append(getRetainCardInfo(), that.getRetainCardInfo())
                .append(getRestoreTime(), that.getRestoreTime())
                .append(getRetainTime(), that.getRetainTime())
                .append(getPosition(), that.getPosition())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getNumber())
                .append(getStatus())
                .append(isRetained())
                .append(getCardInfo())
                .append(getRetainCardInfo())
                .append(getRestoreTime())
                .append(getRetainTime())
                .append(getPosition())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", getNumber())
                .append("status", getStatus())
                .append("isRetained", isRetained())
                .append("cardInfo", getCardInfo())
                .append("retainCardInfo", getRetainCardInfo())
                .append("restoreTime", getRestoreTime())
                .append("retainTime", getRetainTime())
                .append("position", getPosition())
                .toString();
    }

    public static class Builder {
        private final int number;
        private final SlotUnitInfoStatus status;
        private final int isRetained;
        private final String cardInfo;
        private final String retainCardInfo;
        private final String restoreTime;
        private final String retainTime;
        private final String position;

        public Builder(int number, SlotUnitInfoStatus status, int isRetained, String cardInfo, String retainCardInfo, String restoreTime, String retainTime, String position) {
            this.number = number;
            this.status = status;
            this.isRetained = isRetained;
            this.cardInfo = cardInfo;
            this.retainCardInfo = retainCardInfo;
            this.restoreTime = restoreTime;
            this.retainTime = retainTime;
            this.position = position;
        }

        public SlotUnitInfo310 build() {
            return new SlotUnitInfo310(this);
        }
    }
}
