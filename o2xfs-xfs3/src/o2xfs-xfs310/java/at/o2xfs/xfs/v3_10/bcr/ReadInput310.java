package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.bcr.Symbologies;
import at.o2xfs.xfs.bcr.Symbology;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReadInput310 extends Struct {

    public static class Builder {
        private final Symbology[] symbologies;

        public Builder(Symbology[] symbologies) {
            this.symbologies = symbologies;
        }

        public ReadInput310 build() {
            return new ReadInput310(this);
        }
    }

    protected final Pointer symbologies = new Pointer();
    protected ReadInput310(){
        add(symbologies);
    }

    protected ReadInput310(Builder builder) {
        this();
        allocate();
        symbologies.pointTo(new Symbologies(builder.symbologies));
    }

    public Symbology[] getSymbologies() {
        return new Symbologies(symbologies).get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getSymbologies()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("symbologies", getSymbologies()).toString();
    }
}
