package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINExportItemType;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExportRSAIssuerSignedItem3 extends XfsStruct {

    public static class Builder {
        private PINExportItemType exportItemType;
        private String name;

        public Builder() {
        }

        public Builder exportItemType(PINExportItemType exportItemType) {
            this.exportItemType = exportItemType;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public ExportRSAIssuerSignedItem3 build() {
            return new ExportRSAIssuerSignedItem3(this);
        }
    }

    private XfsWord<PINExportItemType> exportItemType = new XfsWord<>(PINExportItemType.class);
    private LPSTR name = new LPSTR();

    private ExportRSAIssuerSignedItem3() {
        add(exportItemType).add(name);
    }

    public ExportRSAIssuerSignedItem3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public ExportRSAIssuerSignedItem3(Builder builder) {
        this();
        allocate();
        this.exportItemType.set(builder.exportItemType);
        this.name.set(builder.name);
    }

    public PINExportItemType getExportItemType() {
        return exportItemType.get();
    }

    public void setExportItemType(PINExportItemType exportItemType) {
        this.exportItemType.set(exportItemType);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("exportItemType", getExportItemType())
                .append("name", getName())
                .toString();
    }
}
