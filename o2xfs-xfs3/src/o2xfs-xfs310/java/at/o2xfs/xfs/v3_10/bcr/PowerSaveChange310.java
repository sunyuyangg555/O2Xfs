package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PowerSaveChange310 extends Struct {

	protected final USHORT powerSaveRecoveryTime = new USHORT();

	protected PowerSaveChange310() {
		add(powerSaveRecoveryTime);
	}

	public PowerSaveChange310(Pointer p) {
		this();
		assignBuffer(p);
	}

	public PowerSaveChange310(PowerSaveChange310 copy) {
		this();
		allocate();
		set(copy);
	}

	protected void set(PowerSaveChange310 copy) {
		powerSaveRecoveryTime.set(copy.getPowerSaveRecoveryTime());
	}

	public int getPowerSaveRecoveryTime() {
		return powerSaveRecoveryTime.get();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPowerSaveRecoveryTime()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PowerSaveChange310) {
			PowerSaveChange310 powerSaveChange = (PowerSaveChange310) obj;
			return new EqualsBuilder().append(getPowerSaveRecoveryTime(), powerSaveChange.getPowerSaveRecoveryTime()).isEquals();
		}
		return false;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("powerSaveRecoveryTime", getPowerSaveRecoveryTime()).toString();
	}
}
