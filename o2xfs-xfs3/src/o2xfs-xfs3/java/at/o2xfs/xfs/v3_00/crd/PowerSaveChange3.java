package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * typedef struct _wfs_crd_power_save_change
 * {
 *  USHORT usPowerSaveRecoveryTime;
 * } WFSCRDPOWERSAVECHANGE, *LPWFSCRDPOWERSAVECHANGE;
 */
public class PowerSaveChange3 extends Struct {

    protected final USHORT powerSaveRecoveryTime = new USHORT();

    protected PowerSaveChange3() {
        add(powerSaveRecoveryTime);
    }

    public PowerSaveChange3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public PowerSaveChange3(PowerSaveChange3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(PowerSaveChange3 copy) {
        powerSaveRecoveryTime.set(copy.getPowerSaveRecoveryTime());
    }

    public int getPowerSaveRecoveryTime() {
        return powerSaveRecoveryTime.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PowerSaveChange3)) return false;

        PowerSaveChange3 reset3 = (PowerSaveChange3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getPowerSaveRecoveryTime(), reset3.getPowerSaveRecoveryTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getPowerSaveRecoveryTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("powerSaveRecoveryTime", getPowerSaveRecoveryTime())
                .toString();
    }
}
