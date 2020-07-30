package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *typedef struct _wfs_crd_power_save_control
 * {
 *  USHORT usMaxPowerSaveRecoveryTime;
 * } WFSCRDPOWERSAVECONTROL, *LPWFSCRDPOWERSAVECONTROL;
 */
public class PowerSaveControl3 extends Struct {

    public PowerSaveControl3(Builder builder) {
        this();
        allocate();
        masPowerSaveRecoveryTime.set(builder.masPowerSaveRecoveryTime);
    }

    public static class Builder {
        private int masPowerSaveRecoveryTime;
        public Builder(int masPowerSaveRecoveryTime) {
            this.masPowerSaveRecoveryTime = masPowerSaveRecoveryTime;
        }

        public PowerSaveControl3 build() {
            return new PowerSaveControl3(this);
        }
    }

    protected USHORT masPowerSaveRecoveryTime = new USHORT();

    protected PowerSaveControl3() {
        add(masPowerSaveRecoveryTime);
    }

    public PowerSaveControl3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public PowerSaveControl3(PowerSaveControl3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(PowerSaveControl3 copy) {
        masPowerSaveRecoveryTime.set(copy.getMasPowerSaveRecoveryTime());
    }

    public int getMasPowerSaveRecoveryTime() {
        return masPowerSaveRecoveryTime.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PowerSaveControl3)) return false;

        PowerSaveControl3 that = (PowerSaveControl3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getMasPowerSaveRecoveryTime(), that.getMasPowerSaveRecoveryTime())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getMasPowerSaveRecoveryTime())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("masPowerSaveRecoveryTime", getMasPowerSaveRecoveryTime())
                .toString();
    }
}
