package at.o2xfs.xfs.pin;

import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.win32.XfsWordBitmask;

import java.util.Set;

public class WfsPinImportKey extends XfsStruct {

    private LPSTR key = new LPSTR();
    private LPSTR encKey = new LPSTR();
    private Pointer ident = new Pointer();
    private Pointer value = new Pointer();
    private XfsWordBitmask<PINUse> use = new XfsWordBitmask<>(PINUse.class);

    public WfsPinImportKey() {
        add(key)
                .add(encKey)
                .add(ident)
                .add(value)
                .add(use);
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
}
