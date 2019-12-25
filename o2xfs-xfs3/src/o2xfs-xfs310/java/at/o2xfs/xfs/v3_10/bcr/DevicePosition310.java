package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.bcr.Position;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DevicePosition310 extends Struct {
    private final XfsWord<Position> position = new XfsWord<>(Position.class);

    protected DevicePosition310() {
        add(position);
    }

    public DevicePosition310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public DevicePosition310(DevicePosition310 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(DevicePosition310 copy) {
        position.set(copy.getPosition());
    }

    public Position getPosition() {
        return position.get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getPosition()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DevicePosition310) {
            DevicePosition310 devicePosition = (DevicePosition310) obj;
            return new EqualsBuilder().append(getPosition(), devicePosition.getPosition()).isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("position", getPosition()).toString();
    }
}
