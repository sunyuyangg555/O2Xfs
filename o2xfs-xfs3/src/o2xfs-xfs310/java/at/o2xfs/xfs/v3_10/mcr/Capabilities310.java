package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.win32.WORD;
import at.o2xfs.xfs.mcr.Type;
import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class Capabilities310 extends Struct {
    /**
     * 设备类型为 WFS_SERIVCE_CLASS_IDC
     */
    protected final WORD serviceClass = new WORD(2);
    protected final XfsWord<Type> type = new XfsWord<>(Type.class);
    /**
     * 可存储的大封包的槽位个数
     */
    protected final USHORT bigSlots = new USHORT();
    /**
     * 可存储小封包的槽位个数
     */
    protected final USHORT normalSlots = new USHORT();
    /**
     * 自定义信息既扩展信息
     */
    protected final XfsMultiByteMap extra = new XfsMultiByteMap();

    protected Capabilities310() {
        add(serviceClass);
        add(type);
        add(bigSlots);
        add(normalSlots);
        add(extra);
    }

    public Capabilities310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Capabilities310(Capabilities310 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(Capabilities310 copy) {
        type.set(copy.getType());
        bigSlots.set(copy.getBigSlots());
        normalSlots.set(copy.getNormalSlots());
        extra.set(copy.getExtra());
    }


    public Type getType() {
        return type.get();
    }

    public int getBigSlots() {
        return bigSlots.get();
    }

    public int getNormalSlots() {
        return normalSlots.get();
    }

    public Map<String, String> getExtra() {
        return extra.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Capabilities310 that = (Capabilities310) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(serviceClass, that.serviceClass)
                .append(getType(), that.getType())
                .append(getBigSlots(), that.getBigSlots())
                .append(getNormalSlots(), that.getNormalSlots())
                .append(getExtra(), that.getExtra())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(serviceClass)
                .append(getType())
                .append(getBigSlots())
                .append(getNormalSlots())
                .append(getExtra())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("serviceClass", serviceClass)
                .append("type", getType())
                .append("bigSlots", getBigSlots())
                .append("normalSlots", getNormalSlots())
                .append("extra", getExtra())
                .toString();
    }
}
