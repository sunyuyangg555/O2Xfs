package at.o2xfs.xfs.v3_00.cim;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.win32.XfsPointerArray;

public class Output3Array extends XfsPointerArray<Output3> {

    public Output3Array(Output3[] array) {
        super(array);
    }

    public Output3Array(Pointer p, int length) {
        super(p, length);
    }

    @Override
    public Output3 copy(Output3 copy) {
        return new Output3(copy);
    }

    @Override
    public Output3[] get() {
        Output3[] result = new Output3[pointers.length];
        for (int i = 0; i < pointers.length; i++) {
            result[i] = copy(new Output3(pointers[i]));
        }
        return result;
    }
}
