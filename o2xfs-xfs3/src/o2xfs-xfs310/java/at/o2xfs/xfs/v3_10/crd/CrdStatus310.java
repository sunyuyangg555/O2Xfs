package at.o2xfs.xfs.v3_10.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.crd.CrdConstant;
import at.o2xfs.xfs.crd.DevicePosition;
import at.o2xfs.xfs.crd.GuidLight;
import at.o2xfs.xfs.v3_00.crd.CrdStatus3;
import at.o2xfs.xfs.win32.XfsBitmaskArray;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Set;

public class CrdStatus310 extends CrdStatus3 {
    protected final XfsBitmaskArray<GuidLight> guidLights = new XfsBitmaskArray<>(CrdConstant.GUIDLIGHTS_SIZE, GuidLight.class);
    protected final XfsWord<DevicePosition> devicePosition = new XfsWord<>(DevicePosition.class);
    protected final USHORT powerSaveRecoveryTime = new USHORT();

    public List<Set<at.o2xfs.xfs.crd.GuidLight>> getGuidLights() {
        return guidLights.get();
    }

    public DevicePosition getDevicePosition() {
        return devicePosition.get();
    }

    public int getPowerSaveRecoveryTime() {
        return powerSaveRecoveryTime.get();
    }

    protected CrdStatus310() {
        add(guidLights);
        add(devicePosition);
        add(powerSaveRecoveryTime);
    }

    public CrdStatus310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CrdStatus310(CrdStatus310 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(CrdStatus310 copy) {
        super.set(copy);
        guidLights.set(copy.getGuidLights());
        devicePosition.set(copy.getDevicePosition());
        powerSaveRecoveryTime.set(copy.getPowerSaveRecoveryTime());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getGuidLights())
                .append(getDevicePosition())
                .append(getPowerSaveRecoveryTime())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrdStatus310) {
            CrdStatus310 crdStatus310 = (CrdStatus310) obj;
            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(getGuidLights(), crdStatus310.getGuidLights())
                    .append(getDevicePosition(), crdStatus310.getDevicePosition())
                    .append(getPowerSaveRecoveryTime(), crdStatus310.getPowerSaveRecoveryTime())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("guidLights", getGuidLights())
                .append("devicePosition", getDevicePosition())
                .append("powerSaveRecoveryTime", getPowerSaveRecoveryTime())
                .toString();
    }
}
