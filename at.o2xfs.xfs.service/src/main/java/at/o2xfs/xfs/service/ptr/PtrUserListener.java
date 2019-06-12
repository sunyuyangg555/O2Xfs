package at.o2xfs.xfs.service.ptr;

import at.o2xfs.xfs.ptr.Ink;
import at.o2xfs.xfs.ptr.Lamp;
import at.o2xfs.xfs.ptr.Toner;
import at.o2xfs.xfs.v3_00.ptr.BinThreshold3;
import at.o2xfs.xfs.v3_00.ptr.PaperThreshold3;

public interface PtrUserListener {

    void onRetractBinThreshold(BinThreshold3 binThreshold3);

    void onPaperThreshold(PaperThreshold3 paperThreshold3);

    void onTonerThreshold(Toner toner);

    void onLampThreshold(Lamp lamp);

    void onInkThreshold(Ink ink);
}
