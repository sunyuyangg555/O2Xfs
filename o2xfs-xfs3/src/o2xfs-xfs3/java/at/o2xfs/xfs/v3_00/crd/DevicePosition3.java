package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.crd.DevicePosition;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_device_position
 * {
 *  WORD wPosition;
 * } WFSCRDDEVICEPOSITION, *LPWFSCRDDEVICEPOSITION;
 */
public class DevicePosition3 extends Struct {

    protected final XfsWord<DevicePosition> position = new XfsWord<>(DevicePosition.class);

    protected DevicePosition3() {
        add(position);
    }

    public DevicePosition3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public DevicePosition3(DevicePosition3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(DevicePosition3 copy) {
        position.set(copy.getPosition());
    }

    private DevicePosition getPosition() {
        return position.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof DevicePosition3)) return false;

        DevicePosition3 that = (DevicePosition3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(position, that.position)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(position)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("position", getPosition())
                .toString();
    }
}
