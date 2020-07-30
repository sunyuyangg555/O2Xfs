package at.o2xfs.xfs.v3_10.crd;

import at.o2xfs.win32.BOOL;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.ptr.GuidLight;
import at.o2xfs.xfs.ptr.PtrConstant;
import at.o2xfs.xfs.v3_00.crd.CrdCapabilities3;
import at.o2xfs.xfs.win32.XfsBitmaskArray;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Set;

public class CrdCapabilities310 extends CrdCapabilities3 {
    protected final XfsBitmaskArray<GuidLight> guidLights = new XfsBitmaskArray<>(PtrConstant.GUIDLIGHTS_SIZE,
            GuidLight.class);
    protected final BOOL powerSaveControl = new BOOL();

    protected CrdCapabilities310() {
        add(guidLights);
        add(powerSaveControl);
    }

    public CrdCapabilities310(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CrdCapabilities310(CrdCapabilities310 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(CrdCapabilities310 copy) {
        super.set(copy);
        guidLights.set(copy.getGuidLights());
        powerSaveControl.set(copy.isPowerSaveControl());

    }

    public List<Set<GuidLight>> getGuidLights() {
        return guidLights.get();
    }
    public boolean isPowerSaveControl() {
        return powerSaveControl.get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getGuidLights())
                .append(isPowerSaveControl())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrdCapabilities310) {
            CrdCapabilities310 crdCapabilities310 = (CrdCapabilities310) obj;
            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(getGuidLights(), crdCapabilities310.getGuidLights())
                    .append(isPowerSaveControl(), crdCapabilities310.isPowerSaveControl())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("guidLights", getGuidLights())
                .append("powerSaveControl", isPowerSaveControl())
                .toString();
    }
}
