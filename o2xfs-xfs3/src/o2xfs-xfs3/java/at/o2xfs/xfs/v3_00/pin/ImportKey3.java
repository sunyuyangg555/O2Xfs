package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.common.Bytes;
import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINUse;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.win32.XfsWordBitmask;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Set;

public class ImportKey3 extends XfsStruct {

    public static class Builder {
        private String key = "";
        private String encKey = "";
        private byte[] ident;
        private byte[] value;
        private Set<PINUse> useSet = new HashSet<>();

        public Builder(String key) {
            this.key = key;
        }

        public Builder encKey(String encKey) {
            this.encKey = encKey;
            return this;
        }

        public Builder ident(byte[] ident) {
            this.ident = Bytes.copy(ident);
            return this;
        }

        public Builder value(byte[] value) {
            this.value = Bytes.copy(value);
            return this;
        }

        public Builder use(Set<PINUse> useSet) {
            this.useSet.addAll(useSet);
            return this;
        }

        public ImportKey3 build() {
            return new ImportKey3(this);
        }
    }

    private LPSTR key = new LPSTR();
    private LPSTR encKey = new LPSTR();
    private Pointer ident = new Pointer();
    private Pointer value = new Pointer();
    private XfsWordBitmask<PINUse> use = new XfsWordBitmask<>(PINUse.class);

    protected ImportKey3() {
        add(key)
                .add(encKey)
                .add(ident)
                .add(value)
                .add(use);
    }

    public ImportKey3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public ImportKey3(ImportKey3 copy) {
        this();
        allocate();
        set(copy);
    }

    public ImportKey3(Builder builder) {
        this();
        allocate();
        key.set(builder.key);
        encKey.set(builder.encKey);
        ident.pointTo(new WfsXData(builder.ident));
        value.pointTo(new WfsXData(builder.value));
        use.set(builder.useSet);
    }

    protected void set(ImportKey3 copy) {
        key.set(copy.getKey());
        encKey.set(copy.getEncKey());
        ident.pointTo(new WfsXData(copy.getIndent()));
        value.pointTo(new WfsXData(copy.getValue()));
        use.set(copy.getUse());
    }

    public Set<PINUse> getUse() {
        return this.use.get();
    }

    public void setUse(Set<PINUse> useSet) {
        this.use.set(useSet);
    }

    public String getKey() {
        return key.toString();
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getEncKey() {
        return encKey.toString();
    }

    public void setEncKey(String encKey) {
        this.encKey.set(encKey);
    }

    public void setIdent(byte[] ident) {
        this.ident.pointTo(new WfsXData(ident));
    }

    public byte[] getIndent() {
        byte[] result = null;
        if (!Pointer.NULL.equals(ident)) {
            result = new WfsXData(ident).getData();
        }
        return result;
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
                .append("encKey", getEncKey())
                .append("ident", javax.xml.bind.DatatypeConverter.printHexBinary(getIndent()))
                .append("value", javax.xml.bind.DatatypeConverter.printHexBinary(getValue()))
                .append("use", getUse())
                .toString();
    }
}
