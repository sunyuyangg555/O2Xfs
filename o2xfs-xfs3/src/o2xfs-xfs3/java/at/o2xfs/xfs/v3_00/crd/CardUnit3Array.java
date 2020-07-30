package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.win32.XfsPointerArray;

public class CardUnit3Array extends XfsPointerArray<CardUnit3> {

    public CardUnit3Array(CardUnit3[] array) {
        super(array);
    }

    public CardUnit3Array(Pointer p, int length) {
        super(p, length);
    }

    @Override
    public CardUnit3 copy(CardUnit3 copy) {
        return new CardUnit3(copy);
    }

    @Override
    public CardUnit3[] get() {
        CardUnit3[] result = new CardUnit3[pointers.length];
        for (int i = 0; i < pointers.length; i++) {
            result[i] = copy(new CardUnit3(pointers[i]));
        }
        return result;
    }
}
