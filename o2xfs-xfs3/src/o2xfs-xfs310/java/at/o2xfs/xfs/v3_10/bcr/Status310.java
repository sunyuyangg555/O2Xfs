package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.bcr.DeviceState;
import at.o2xfs.xfs.bcr.GuidLight;
import at.o2xfs.xfs.bcr.Position;
import at.o2xfs.xfs.bcr.Scanner;
import at.o2xfs.xfs.win32.XfsBitmaskArray;
import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Status310 extends Struct {

    private static final int GUIDLIGHTS_SIZE = 32;

    protected final XfsWord<DeviceState> device = new XfsWord<>(DeviceState.class);
    protected final XfsWord<Scanner> bcrScanner = new XfsWord<>(Scanner.class);
    protected final XfsBitmaskArray<GuidLight> guidLights = new XfsBitmaskArray<>(GUIDLIGHTS_SIZE, GuidLight.class);
    protected final XfsMultiByteMap extra = new XfsMultiByteMap();
    protected final XfsWord<Position> devicePosition = new XfsWord<>(Position.class);
    protected final USHORT powerSaveRecoveryTime = new USHORT();

    protected Status310() {
        add(device);
        add(bcrScanner);
        add(guidLights);
        add(extra);
        add(devicePosition);
        add(powerSaveRecoveryTime);
    }

    public Status310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Status310(Status310 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(Status310 copy) {
        device.set(copy.getDeviceState());
        bcrScanner.set(copy.getScanner());
        guidLights.set(copy.getGuidLights());
        extra.set(copy.getExtra());
        devicePosition.set(copy.getPosition());
        powerSaveRecoveryTime.set(copy.getPowerSaveRecoveryTime());
    }

    public DeviceState getDeviceState() {
        return device.get();
    }

    public Scanner getScanner() {
        return bcrScanner.get();
    }

    public List<Set<GuidLight>> getGuidLights() {
        return guidLights.get();
    }

    public Map<String, String> getExtra() {
        return extra.get();
    }

    public Position getPosition() {
        return devicePosition.get();
    }

    public int getPowerSaveRecoveryTime() {
        return powerSaveRecoveryTime.get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDeviceState())
                .append(getScanner())
                .append(getGuidLights())
                .append(getExtra())
                .append(getPosition())
                .append(getPowerSaveRecoveryTime())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Status310) {
            Status310 status310 = (Status310) obj;
            return new EqualsBuilder()
                    .append(getDeviceState(), status310.getDeviceState())
                    .append(getScanner(), status310.getScanner())
                    .append(getGuidLights(), status310.getGuidLights())
                    .append(getExtra(), status310.getExtra())
                    .append(getPosition(), status310.getPosition())
                    .append(getPowerSaveRecoveryTime(), status310.getPowerSaveRecoveryTime())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("deviceState", getDeviceState())
                .append("bcrScanner", getScanner())
                .append("guidLights", getGuidLights())
                .append("extra", getExtra())
                .append("position", getPosition())
                .append("powerSaveRecoveryTime", getPowerSaveRecoveryTime())
                .toString();
    }
}
