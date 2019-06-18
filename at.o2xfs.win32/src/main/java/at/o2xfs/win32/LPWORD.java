package at.o2xfs.win32;

public class LPWORD extends Pointer implements ValueType<Short> {

    public LPWORD(Short value) {
        super();
        allocate();
        set(value);
    }


    @Override
    public void set(Short value) {
        WORD word = new WORD();
        word.allocate();
        word.set(value);
        pointTo(word);
    }

    @Override
    public Short get() {
        return Bits.getShort(buffer(WORD.SIZE).get());
    }
}
