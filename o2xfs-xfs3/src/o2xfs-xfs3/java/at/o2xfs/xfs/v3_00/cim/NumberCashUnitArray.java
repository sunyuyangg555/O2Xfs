package at.o2xfs.xfs.v3_00.cim;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.USHORT;
import at.o2xfs.xfs.win32.XfsPointerArray;

public class NumberCashUnitArray extends XfsPointerArray<USHORT> {

    public NumberCashUnitArray(USHORT[] array) {
        super(array);
    }

    public NumberCashUnitArray(Pointer p, int length) {
        super(p, length);
    }

    @Override
    public USHORT copy(USHORT copy) {
        return new USHORT(copy.get());
    }

    @Override
    public USHORT[] get() {
        USHORT[] result = new USHORT[pointers.length];
        for (int i = 0; i < pointers.length; i++) {
            result[i] = copy(new USHORT(pointers[i]));
        }
        return result;
    }
}
