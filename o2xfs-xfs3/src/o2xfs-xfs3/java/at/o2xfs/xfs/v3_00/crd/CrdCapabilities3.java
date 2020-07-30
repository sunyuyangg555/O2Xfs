package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.BOOL;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.WORD;
import at.o2xfs.xfs.crd.*;
import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * typedef struct _wfs_crd_caps
 * {
 *  WORD wClass;
 *  BOOL bCompound;
 *  WORD fwPowerOnOption;
 *  WORD fwPowerOffOption;
 *  BOOL bCardTakenSensor;
 *  WORD fwDispenseTo;
 *  LPSTR lpszExtra;
 *  DWORD dwGuidLights[WFS_CRD_GUIDLIGHTS_SIZE];
 *  BOOL bPowerSaveControl;
 * } WFSCRDCAPS, *LPWFSCRDCAPS;
 *
 */
public class CrdCapabilities3 extends Struct {
    protected final WORD serviceClass = new WORD();
    protected final BOOL compound = new BOOL();
    protected final XfsWord<PowerOnOption> powerOnOption = new XfsWord<>(PowerOnOption.class);
    protected final XfsWord<PowerOffOption> powerOffOption = new XfsWord<>(PowerOffOption.class);
    protected final BOOL cardTakenSensor = new BOOL();
    protected final XfsWord<DispenseTo> dispenseTo = new XfsWord<>(DispenseTo.class);
    protected final XfsMultiByteMap extra = new XfsMultiByteMap();
    
    public int getServiceClass() {
        return serviceClass.get();
    }
    
    public boolean isCompound() {
        return compound.get();
    }
    
    public PowerOnOption getPowerOnOption() {
        return powerOnOption.get();
    }
    
    public PowerOffOption getPowerOffOption() {
        return powerOffOption.get();
    }
    
    public boolean isCardTakenSensor() {
        return cardTakenSensor.get();
    }
    
    public DispenseTo getDispenseTo() {
        return dispenseTo.get();
    }

    public Map<String, String> getExtra() {
        return extra.get();
    }

    protected CrdCapabilities3() {
        add(serviceClass);
        add(compound);
        add(powerOnOption);
        add(powerOffOption);
        add(cardTakenSensor);
        add(dispenseTo);
        add(extra);
    }
    

    public CrdCapabilities3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CrdCapabilities3(CrdCapabilities3 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(CrdCapabilities3 copy) {
        serviceClass.set(copy.getServiceClass());
        compound.set(copy.isCompound());
        powerOnOption.set(copy.getPowerOnOption());
        powerOffOption.set(copy.getPowerOffOption());
        cardTakenSensor.set(copy.isCardTakenSensor());
        dispenseTo.set(copy.getDispenseTo());
        extra.set(copy.getExtra());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getServiceClass())
                .append(isCompound())
                .append(getPowerOnOption())
                .append(getPowerOffOption())
                .append(isCardTakenSensor())
                .append(getDispenseTo())
                .append(getExtra())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrdCapabilities3) {
            CrdCapabilities3 crdCapabilities3 = (CrdCapabilities3) obj;
            return new EqualsBuilder()
                    .append(getServiceClass(), crdCapabilities3.getServiceClass())
                    .append(isCompound(), crdCapabilities3.isCompound())
                    .append(getPowerOnOption(), crdCapabilities3.getPowerOnOption())
                    .append(getPowerOffOption(), crdCapabilities3.getPowerOffOption())
                    .append(isCardTakenSensor(), crdCapabilities3.isCardTakenSensor())
                    .append(getDispenseTo(), crdCapabilities3.getDispenseTo())
                    .append(getExtra(), crdCapabilities3.getExtra())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("serviceClass", getServiceClass())
                .append("compound", isCompound())
                .append("powerOnOption", getPowerOnOption())
                .append("powerOffOption", getPowerOffOption())
                .append("cardTakenSensor", isCardTakenSensor())
                .append("dispenseTo", getDispenseTo())
                .append("extra", getExtra())
                .toString();
    }
}
