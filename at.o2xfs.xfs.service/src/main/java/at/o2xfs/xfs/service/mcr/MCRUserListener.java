package at.o2xfs.xfs.service.mcr;

import at.o2xfs.xfs.mcr.RetainBin;

public interface MCRUserListener {

    void onRetainBinThreshold(RetainBin retainBin);
}
