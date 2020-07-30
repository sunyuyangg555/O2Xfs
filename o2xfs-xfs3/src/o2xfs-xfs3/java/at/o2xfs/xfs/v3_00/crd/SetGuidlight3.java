package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.WORD;
import at.o2xfs.xfs.crd.*;
import at.o2xfs.xfs.win32.XfsDWordBitmask;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 *
 * typedef struct _wfs_crd_set_guidlight
 * {
 *  WORD wGuidLight;
 *  DWORD dwCommand;
 * } WFSCRDSETGUIDLIGHT, *LPWFSCRDSETGUIDLIGHT;
 *
 */
public class SetGuidlight3 extends Struct {

    protected final WORD guidLight = new WORD();
    protected final XfsDWordBitmask<GuidLight> command = new XfsDWordBitmask<>(GuidLight.class);

    protected SetGuidlight3() {
        add(guidLight);
        add(command);
    }

    public SetGuidlight3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public SetGuidlight3(SetGuidlight3 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(SetGuidlight3 copy) {
        guidLight.set(copy.getGuidLight());
        command.set(copy.getCommand());
    }

    public int getGuidLight() {
        return guidLight.get();
    }

    public Set<GuidLight> getCommand() {
        return command.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SetGuidlight3)) return false;

        SetGuidlight3 that = (SetGuidlight3) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getGuidLight(), that.getGuidLight())
                .append(getCommand(), that.getCommand())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getGuidLight())
                .append(getCommand())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("guidLight", getGuidLight())
                .append("command", getCommand())
                .toString();
    }
}
