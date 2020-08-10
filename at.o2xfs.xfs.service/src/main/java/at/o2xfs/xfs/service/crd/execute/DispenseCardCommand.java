package at.o2xfs.xfs.service.crd.execute;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.crd.CrdExecuteCommand;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.crd.CRDService;
import at.o2xfs.xfs.service.util.ExceptionUtil;
import at.o2xfs.xfs.v3_00.crd.Dispense3;


public class DispenseCardCommand extends AbstractAsyncXfsCommand<DispenseCardListener, SuccessEvent> {

    private final CRDService crdService;
    private final Dispense3 dispense3;

    public DispenseCardCommand(CRDService crdService, Dispense3 dispense3) {
        if (crdService == null) {
            ExceptionUtil.nullArgument("crdService");
        }
        this.crdService = crdService;
        this.dispense3 = dispense3;
    }


    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(crdService, CrdExecuteCommand.DISPENSE_CARD, dispense3);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }
}
