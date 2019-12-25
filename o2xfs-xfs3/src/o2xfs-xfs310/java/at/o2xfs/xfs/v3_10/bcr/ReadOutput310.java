package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.bcr.Symbology;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReadOutput310 extends Struct {

    protected final XfsWord<Symbology> symbology = new XfsWord<>(Symbology.class);
    protected final Pointer barcodeData = new Pointer();
    protected final LPSTR symbologyName = new LPSTR();

    protected ReadOutput310() {
        add(symbology);
        add(barcodeData);
        add(symbologyName);
    }

    protected ReadOutput310(Pointer p) {
        this();
        assignBuffer(p);
    }

    protected ReadOutput310(ReadOutput310 copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(ReadOutput310 copy) {
        symbology.set(copy.getSymbology());
        barcodeData.pointTo(new BcrXData(copy.barcodeData));
        symbologyName.set(copy.getSymbologyName());
    }

    public Symbology getSymbology() {
        return symbology.get();
    }

    public byte[] getBarcodeData() {
        return new BcrXData(barcodeData).getData();
    }

    public String getSymbologyName() {
        return symbologyName.get();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getSymbology())
                .append(getBarcodeData())
                .append(getSymbologyName())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbology", getSymbology())
                .append("barcodeData", getBarcodeData())
                .append("symbologyName", getSymbologyName())
                .toString();
    }
}
