package at.o2xfs.xfs.v3_10.bcr;

import at.o2xfs.win32.ByteArray;
import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.win32.USHORT;

public class BcrXData extends Struct {

    protected final USHORT length = new USHORT();
    protected final Pointer data = new Pointer();

    protected BcrXData() {
        add(length);
        add(data);
    }

    public BcrXData(Pointer p) {
        this();
        assignBuffer(p);
    }

    public BcrXData(BcrXData copy) {
        this();
        allocate();
        set(copy);
    }

    private void set(BcrXData copy) {
        length.set(copy.getLength());
        data.pointTo(new ByteArray(copy.getData()));
    }

    public int getLength() {
        return length.get();
    }

    public byte[] getData() {
        return data.buffer(getLength()).get();
    }
}
