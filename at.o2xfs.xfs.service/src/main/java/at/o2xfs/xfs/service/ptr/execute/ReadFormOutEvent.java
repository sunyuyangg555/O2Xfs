package at.o2xfs.xfs.service.ptr.execute;

import at.o2xfs.xfs.service.cmd.event.CompleteEvent;
import at.o2xfs.xfs.v3_00.ptr.ReadFormOut3;

public class ReadFormOutEvent implements CompleteEvent<ReadFormOut3> {

    private final ReadFormOut3 readFormOut3;

    public ReadFormOutEvent(ReadFormOut3 readFormOut3) {
        this.readFormOut3 = readFormOut3;
    }

    public static final ReadFormOutEvent build(ReadFormOut3 readFormOut3) {
        return new ReadFormOutEvent(readFormOut3);
    }

    @Override
    public ReadFormOut3 get() {
        return readFormOut3;
    }
}
