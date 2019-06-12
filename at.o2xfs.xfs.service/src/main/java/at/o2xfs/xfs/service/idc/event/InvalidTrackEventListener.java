package at.o2xfs.xfs.service.idc.event;

import at.o2xfs.xfs.v3_00.idc.TrackEvent3;

public interface InvalidTrackEventListener {
    void onTrackEvent(TrackEvent3 trackEvent3);
}
