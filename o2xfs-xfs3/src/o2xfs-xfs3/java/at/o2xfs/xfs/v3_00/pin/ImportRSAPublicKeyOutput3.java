package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINRSAKeyCheckMode;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ImportRSAPublicKeyOutput3 extends XfsStruct {

    public static class Builder {
        private PINRSAKeyCheckMode rsaKeyCheckMode;
        private byte[] keyCheckValue;

        public Builder() {}

        public Builder rsaKeyCheckMode(PINRSAKeyCheckMode rsaKeyCheckMode) {
            this.rsaKeyCheckMode = rsaKeyCheckMode;
            return this;
        }

        public Builder keyCheckValue(byte[] keyCheckValue) {
            this.keyCheckValue = keyCheckValue;
            return this;
        }

        public ImportRSAPublicKeyOutput3 build() {
            return new ImportRSAPublicKeyOutput3(this);
        }
    }

    private XfsWord<PINRSAKeyCheckMode> rsaKeyCheckMode = new XfsWord<>(PINRSAKeyCheckMode.class);
    private Pointer keyCheckValue = new Pointer();

    public ImportRSAPublicKeyOutput3(){
        add(rsaKeyCheckMode).add(keyCheckValue);
    }

    public ImportRSAPublicKeyOutput3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public ImportRSAPublicKeyOutput3(Builder builder) {
        this();
        allocate();
        this.rsaKeyCheckMode.set(builder.rsaKeyCheckMode);
        this.keyCheckValue.pointTo(new WfsXData(builder.keyCheckValue));
    }

    public PINRSAKeyCheckMode getRsaKeyCheckMode() {
        return rsaKeyCheckMode.get();
    }

    public void setRsaKeyCheckMode(PINRSAKeyCheckMode rsaKeyCheckMode) {
        this.rsaKeyCheckMode.set(rsaKeyCheckMode);
    }

    public void setKeyCheckValue(byte[] keyCheckValue) {
        this.keyCheckValue.pointTo(new WfsXData(keyCheckValue));
    }

    public byte[] getKeyCheckValue() {
        byte[] result = null;
        if (!Pointer.NULL.equals(keyCheckValue)) {
            result = new WfsXData(keyCheckValue).getData();
        }
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("rsaKeyCheckMode", getRsaKeyCheckMode())
                .append("keyCheckValue", javax.xml.bind.DatatypeConverter.printHexBinary(getKeyCheckValue()))
                .toString();
    }
}
