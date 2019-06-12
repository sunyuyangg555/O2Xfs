package at.o2xfs.xfs.service.idc;

import at.o2xfs.xfs.idc.RetainBin;

public interface IDCUserListener {

    void onRetainBinThreshold(RetainBin retainBin);
}
