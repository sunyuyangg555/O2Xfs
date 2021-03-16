package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.mcr.Type;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EjectSlot310 extends Struct {

    protected final XfsWord<Type> type = new XfsWord<>(Type.class);
    /**
     * 槽号或二维码信息
     */
    protected final LPSTR cmdData = new LPSTR();

    protected EjectSlot310() {
        add(type);
        add(cmdData);
    }

    public EjectSlot310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public EjectSlot310(Type type, String cmdData) {
        this();
        allocate();
        this.type.set(type);
        this.cmdData.set(cmdData);
    }
    
    public EjectSlot310(EjectSlot310 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(EjectSlot310 copy) {
        cmdData.set(copy.getCmdData());
        type.set(copy.getType());
    }

    public String getCmdData() {
        return cmdData.get();
    }

    public Type getType() {
        return type.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EjectSlot310 ejectSlot310 = (EjectSlot310) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getType(), ejectSlot310.getType())
                .append(getCmdData(), ejectSlot310.getCmdData())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getType())
                .append(getCmdData())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", getType())
                .append("cmdData", getCmdData())
                .toString();
    }
}
