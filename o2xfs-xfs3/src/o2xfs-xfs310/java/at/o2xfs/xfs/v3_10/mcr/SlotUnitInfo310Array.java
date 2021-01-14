package at.o2xfs.xfs.v3_10.mcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.win32.XfsPointerArray;

public class SlotUnitInfo310Array extends XfsPointerArray<SlotUnitInfo310> {

    public SlotUnitInfo310Array(SlotUnitInfo310[] slotUnitInfo310s) {
        super(slotUnitInfo310s);
    }

    public SlotUnitInfo310Array(Pointer pointer, int length) {
        super(pointer, length);
    }

    @Override
    public SlotUnitInfo310 copy(SlotUnitInfo310 copy) {
        return new SlotUnitInfo310(copy);
    }

    @Override
    public SlotUnitInfo310[] get() {
        SlotUnitInfo310[] result = new SlotUnitInfo310[pointers.length];
        for(int i = 0; i < pointers.length; i++) {
            result[i] = copy(new SlotUnitInfo310(pointers[i]));
        }
        return result;
    }
}
