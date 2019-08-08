package at.o2xfs.xfs.service.pin.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.pin.PINExecuteCommand;
import at.o2xfs.xfs.pin.PINMessage;
import at.o2xfs.xfs.pin.WfsPINData;
import at.o2xfs.xfs.pin.WfsPINKey;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.pin.PINService;
import at.o2xfs.xfs.service.util.ExceptionUtil;


public class PinGetDataCommand  extends AbstractAsyncXfsCommand<PinGetDataListener, PinGetDataCompleteEvent> {

    private final PINService pinService;

    public PinGetDataCommand(PINService pinService) {
        if (pinService == null) {
            ExceptionUtil.nullArgument("pinService");
        }
        this.pinService = pinService;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(pinService, PINExecuteCommand.GET_DATA);
    }

    @Override
    protected PinGetDataCompleteEvent createCompleteEvent(Pointer results) {
        final WfsPINData pinData = new WfsPINData(
                pinService.getXfsVersion(), results);
        return new PinGetDataCompleteEvent(pinData);
    }

    @Override
    public void interIntermediateEvent(WFSResult wfsResult) {
        final PINMessage pinMessage = wfsResult.getEventID(PINMessage.class);
        switch (pinMessage) {
            case WFS_EXEE_PIN_KEY:
                final WfsPINKey pinKey = new WfsPINKey(wfsResult.getResults());
                firePinKey(pinKey);
                break;
            case WFS_EXEE_PIN_ENTERDATA:
                fireEnterData();
                break;

        }
    }

    private void fireEnterData() {
        listeners.stream().forEach(pinGetDataListener -> pinGetDataListener.onEnterData());
    }

    private void firePinKey(WfsPINKey pinKey) {
        listeners.stream().forEach(pinGetDataListener -> pinGetDataListener.onPinKey(pinKey));
    }

}
