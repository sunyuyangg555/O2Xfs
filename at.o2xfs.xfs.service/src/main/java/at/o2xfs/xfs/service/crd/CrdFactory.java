package at.o2xfs.xfs.service.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsVersion;
import at.o2xfs.xfs.v3_00.crd.CardUnitInfo3;
import at.o2xfs.xfs.v3_00.crd.CrdCapabilities3;
import at.o2xfs.xfs.v3_00.crd.CrdStatus3;
import at.o2xfs.xfs.v3_10.crd.CrdCapabilities310;
import at.o2xfs.xfs.v3_10.crd.CrdStatus310;

public enum CrdFactory {

    INSTANCE;

    public static <T> T create(XfsVersion xfsVersion, Pointer p, Class<T> type) {
        return INSTANCE.doCreate(xfsVersion, p, type);
    }

    private <T> T doCreate(XfsVersion xfsVersion, Pointer p, Class<T> type) {
        Object result;
        if(CrdStatus3.class.equals(type)) {
            result = createStatus(xfsVersion, p);
        }else if(CrdCapabilities3.class.equals(type)) {
            result = createCrdCapabilities(xfsVersion, p);
        }else if(CardUnitInfo3.class.equals(type)) {
            result = createCardUnitInfo(p);
        } else {
            throw new IllegalArgumentException(type.toString());
        }
        return type.cast(result);
    }

    private CardUnitInfo3 createCardUnitInfo(Pointer p) {
        return new CardUnitInfo3(new CardUnitInfo3(p));
    }

    private CrdCapabilities3 createCrdCapabilities(XfsVersion xfsVersion, Pointer p) {
        CrdCapabilities3 result;
        if(xfsVersion.compareTo(XfsVersion.V3_10) >= 0) {
            result = new CrdCapabilities310(new CrdCapabilities310(p));
        } else {
            result = new CrdCapabilities3(new CrdCapabilities3(p));
        }
        return result;
    }

    private CrdStatus3 createStatus(XfsVersion xfsVersion, Pointer p) {
        CrdStatus3 result;
        if(xfsVersion.compareTo(XfsVersion.V3_10) >= 0) {
            result = new CrdStatus310(new CrdStatus310(p));
        } else {
            result = new CrdStatus3(new CrdStatus3(p));
        }
        return result;
    }
}
