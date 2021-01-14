package at.o2xfs.xfs.service.mcr;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsVersion;
import at.o2xfs.xfs.v3_10.mcr.*;

public enum McrFactory {
    INSTANCE;

    public static <T> T create(XfsVersion xfsVersion, Pointer p, Class<T> type) {
        return INSTANCE.doCreate(xfsVersion, p, type);
    }

    private <T> T doCreate(XfsVersion xfsVersion, Pointer p, Class<T> type) {
        Object result;
        if (Status310.class.equals(type)) {
            result = new Status310(new Status310(p));
        } else if (Capabilities310.class.equals(type)) {
            result = new Capabilities310(new Capabilities310(p));
        } else if (SlotsInfo310.class.equals(type)) {
            result = new SlotsInfo310(new SlotsInfo310(p));
        } else if(RetainSlotStartOut.class.equals(type)){
            result = new RetainSlotStartOut(new RetainSlotStartOut(p));
        } else if(EjectSlot.class.equals(type)) {
            result = new EjectSlot(new EjectSlot(p));
        }else {
            throw new IllegalArgumentException(type.toString());
        }
        return type.cast(result);
    }

}
