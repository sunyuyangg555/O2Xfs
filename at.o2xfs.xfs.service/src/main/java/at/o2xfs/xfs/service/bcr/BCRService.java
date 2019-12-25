package at.o2xfs.xfs.service.bcr;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.bcr.BcrMessage;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.v3_10.bcr.DevicePosition310;
import at.o2xfs.xfs.v3_10.bcr.PowerSaveChange310;


public class BCRService extends XfsService<BCRServiceListener, BCRUserListener> {

    private static final Logger LOG = LoggerFactory.getLogger(BCRService.class);

    public BCRService(String logicalName) {
        super(logicalName, XfsServiceClass.BCR);
    }

    @Override
    public void fireServiceEvent(WFSResult wfsResult) {
        BcrMessage message = wfsResult.getEventID(BcrMessage.class);
        switch (message) {
            case POWER_SAVE_CHANGE:
                firePowerSaveChangeEvent(create(wfsResult.getResults(), PowerSaveChange310.class));
                break;
            case DEVICEPOSITION:
                fireDevicePositionEvent(create(wfsResult.getResults(), DevicePosition310.class));
                break;
            default:
                throw new IllegalArgumentException("BcrMessage: " + message);
        }
    }

    private void fireDevicePositionEvent(DevicePosition310 devicePosition310) {
        for(BCRServiceListener listener: serviceListeners) {
            listener.onDevicePosition(devicePosition310);
        }
    }

    private void firePowerSaveChangeEvent(PowerSaveChange310 powerSaveChange310) {
        for(BCRServiceListener listener: serviceListeners) {
            listener.onPowerSaveChange(powerSaveChange310);
        }
    }

    @Override
    public void fireUserEvent(WFSResult wfsResult) {

    }
}
