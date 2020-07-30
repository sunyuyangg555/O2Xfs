package at.o2xfs.xfs.service.crd;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.crd.CrdMessage;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.v3_00.crd.CardUnit3;
import at.o2xfs.xfs.v3_00.crd.DevicePosition3;
import at.o2xfs.xfs.v3_00.crd.MediaDetected3;
import at.o2xfs.xfs.v3_00.crd.PowerSaveChange3;

public class CrdService extends XfsService<CrdServiceListener, CrdUserListener> {

    protected CrdService(String logicalName) {
        super(logicalName, XfsServiceClass.CRD);
    }

    @Override
    public void fireServiceEvent(WFSResult wfsResult) {
        CrdMessage message = wfsResult.getEventID(CrdMessage.class);
        switch (message) {
            case SRVE_CRD_MEDIAREMOVED:
                fireMediaRemoved();
                break;
            case SRVE_CRD_CARDUNITINFOCHANGED:
                fireMediaDetected(create(wfsResult.getResults(), MediaDetected3.class));
                break;
            case SRVE_CRD_MEDIADETECTED:
                fireCardUnitInfoChanged(create(wfsResult.getResults(), CardUnit3.class));
                break;
            case SRVE_CRD_DEVICEPOSITION:
                fireDevicePosition(create(wfsResult.getResults(), DevicePosition3.class));
                break;
            case SRVE_CRD_POWER_SAVE_CHANGE:
                firePowerSaveChange(create(wfsResult.getResults(), PowerSaveChange3.class));
                break;
            default:
                throw new IllegalArgumentException("CrdMessage: " + message);
        }
    }

    private void firePowerSaveChange(PowerSaveChange3 powerSaveChange3) {
        serviceListeners.forEach(crdServiceListener -> crdServiceListener.onPowerSaveChange(powerSaveChange3));

    }

    private void fireDevicePosition(DevicePosition3 devicePosition3) {
        serviceListeners.forEach(crdServiceListener -> crdServiceListener.onDevicePosition(devicePosition3));

    }

    private void fireCardUnitInfoChanged(CardUnit3 cardUnit3) {
        serviceListeners.forEach(crdServiceListener -> crdServiceListener.onCardUnitInfoChanged(cardUnit3));

    }

    private void fireMediaDetected(MediaDetected3 mediaDetected3) {
        serviceListeners.forEach(crdServiceListener -> crdServiceListener.onMediaDetected(mediaDetected3));
    }

    private void fireMediaRemoved() {
        serviceListeners.forEach(CrdServiceListener::onMediaRemoved);
    }

    @Override
    public void fireUserEvent(WFSResult wfsResult) {
        CrdMessage message = wfsResult.getEventID(CrdMessage.class);
        switch (message) {
            case USRE_CRD_CARDUNITTHRESHOLD:
                fireCardUnitThreshold(create(wfsResult.getResults(), CardUnit3.class));
                break;
            default:
                throw new IllegalArgumentException("CrdMessage: " + message);
        }
    }

    private void fireCardUnitThreshold(CardUnit3 cardUnit3) {
        userListeners.forEach(crdUserListener -> crdUserListener.onCardUnitThreshold(cardUnit3));
    }
}
