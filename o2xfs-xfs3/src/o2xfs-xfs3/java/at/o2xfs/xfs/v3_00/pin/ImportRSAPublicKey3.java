package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINRSASignatureAlgorithm;
import at.o2xfs.xfs.pin.PINUse;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ImportRSAPublicKey3 extends XfsStruct {



    public static class Builder {
        private String key;
        private byte[] value;
        private PINUse use;
        private String sigKey;
        private PINRSASignatureAlgorithm rsaSignatureAlgorithm;
        private byte[] signature;

        public Builder() {}

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder value(byte[] value) {
            this.value = value;
            return this;
        }

        public Builder use(PINUse use) {
            this.use = use;
            return this;
        }

        public Builder sigKey(String sigKey) {
            this.sigKey = sigKey;
            return this;
        }

        public Builder rsaSignatureAlgorithm(PINRSASignatureAlgorithm rsaSignatureAlgorithm) {
            this.rsaSignatureAlgorithm = rsaSignatureAlgorithm;
            return this;
        }

        public Builder signature(byte[] signature) {
            this.signature = signature;
            return this;
        }

        public ImportRSAPublicKey3 build() {
            return new ImportRSAPublicKey3(this);
        }

    }

    private LPSTR key = new LPSTR();
    private Pointer value = new Pointer();
    private XfsWord<PINUse> use = new XfsWord<>(PINUse.class);
    private LPSTR sigKey = new LPSTR();
    private XfsWord<PINRSASignatureAlgorithm> rsaSignatureAlgorithm = new XfsWord<>(PINRSASignatureAlgorithm.class);
    private Pointer signature = new Pointer();

    public ImportRSAPublicKey3() {
        add(key)
                .add(value)
                .add(use)
                .add(sigKey)
                .add(rsaSignatureAlgorithm)
                .add(signature);
    }

    public ImportRSAPublicKey3(Builder builder) {
        this();
        allocate();
        this.key.set(builder.key);
        this.value.pointTo(new WfsXData(builder.value));
        this.sigKey.set(builder.sigKey);
        this.rsaSignatureAlgorithm.set(builder.rsaSignatureAlgorithm);
        this.signature.pointTo(new WfsXData(builder.signature));
    }

    public PINUse getUse() {
        return this.use.get();
    }

    public void setUse(PINUse use) {
        this.use.set(use);
    }

    public String getSigKey() {
        return sigKey.get();
    }

    public void setSigKey(String sigKey) {
        this.sigKey.set(sigKey);
    }

    public PINRSASignatureAlgorithm getRsaSignatureAlgorithm() {
        return rsaSignatureAlgorithm.get();
    }

    public void setRsaSignatureAlgorithm(PINRSASignatureAlgorithm rsaSignatureAlgorithm) {
        this.rsaSignatureAlgorithm.set(rsaSignatureAlgorithm);
    }

    public void setSignature(byte[] signature) {
        this.signature.pointTo(new WfsXData(signature));
    }

    public byte[] getSignature() {
        byte[] result = null;
        if (!Pointer.NULL.equals(signature)) {
            result = new WfsXData(signature).getData();
        }
        return result;
    }

    public String getKey() {
        return key.toString();
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public void setValue(byte[] value) {
        this.value.pointTo(new WfsXData(value));
    }

    public byte[] getValue() {
        byte[] result = null;
        if (!Pointer.NULL.equals(value)) {
            result = new WfsXData(value).getData();
        }
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("key", getKey())
                .append("value", javax.xml.bind.DatatypeConverter.printHexBinary(getValue()))
                .append("use", getUse())
                .append("sigKey", getSigKey())
                .append("rsaSignatureAlgorithm", getRsaSignatureAlgorithm())
                .append("signature", javax.xml.bind.DatatypeConverter.printHexBinary(getSignature()))
                .toString();
    }

}
