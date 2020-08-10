package at.o2xfs.xfs.service.ptr.event;

import at.o2xfs.xfs.v3_10.ptr.BinStatus310;

public interface BinStatusListener {
    void onBinStatus(BinStatus310 binStatus310);
}
