package at.o2xfs.xfs.service.cim.event;

import at.o2xfs.xfs.v3_10.cim.ItemInfoSummary310;

import java.util.List;

public interface InfoAvailableListener {
    void onInfoAvailable(List<ItemInfoSummary310> itemInfoSummary310s);
}
